from des import SchedulerDES
from event import Event, EventTypes
from process import ProcessStates

class FCFS(SchedulerDES):
    def scheduler_func(self, cur_event):
        return self.processes[cur_event.process_id]

    def dispatcher_func(self, cur_process):
        cur_process.process_state = ProcessStates.RUNNING
        cur_process.run_for(cur_process.service_time, self.time)
        cur_process.process_state = ProcessStates.TERMINATED
        return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_DONE, event_time=self.time + cur_process.service_time)
        

class SJF(SchedulerDES):
    def scheduler_func(self, cur_event):
        cur_process = None
        for p in self.processes:
            if (p.process_state == ProcessStates.READY and cur_process is None):
                cur_process = p
            elif (p.process_state == ProcessStates.READY) and (p.service_time < cur_process.service_time):
                cur_process = p
        return cur_process

    def dispatcher_func(self, cur_process):
        cur_process.process_state = ProcessStates.RUNNING
        cur_process.run_for(cur_process.service_time, self.time)
        cur_process.process_state = ProcessStates.TERMINATED
        return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_DONE, event_time=self.time + cur_process.service_time)


class RR(SchedulerDES):
    process_index = 0           # Assigned value to loop through the process queue
    def scheduler_func(self, cur_event):
        try:
            while self.processes[self.process_index].process_state != ProcessStates.READY:
                self.process_index += 1
            return self.processes[cur_event.process_id]
        except IndexError:
            self.process_index = 0
            return self.scheduler_func(cur_event)

    def dispatcher_func(self, cur_process):
        quantum = self.quantum
        cur_process.process_state = ProcessStates.RUNNING
        if quantum < cur_process.remaining_time:
            cur_process.run_for(quantum, self.time)
            cur_process.process_state = ProcessStates.READY
            return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_REQ, event_time=self.time + quantum)
        else:
            rem_time = cur_process.remaining_time
            cur_process.run_for(cur_process.remaining_time, self.time)
            cur_process.process_state = ProcessStates.TERMINATED
            return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_DONE, event_time=self.time + rem_time)


class SRTF(SchedulerDES):
    def scheduler_func(self, cur_event):
        cur_process = None
        for p in self.processes:
            if p.process_state == ProcessStates.READY and cur_process is None:
                cur_process = p
            elif p.process_state == ProcessStates.READY and p.remaining_time < cur_process.remaining_time:
                cur_process = p
        return cur_process

    def dispatcher_func(self, cur_process):
        quantum = 1 / self._arrivals_per_time_unit
        cur_process.process_state = ProcessStates.RUNNING
        if quantum < cur_process.remaining_time:
            cur_process.run_for(quantum, self.time)
            cur_process.process_state = ProcessStates.READY
            return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_REQ, event_time=self.time + quantum)
        else:
            rem_time = cur_process.remaining_time
            cur_process.run_for(cur_process.remaining_time, self.time)
            cur_process.process_state = ProcessStates.TERMINATED
            return Event(process_id=cur_process.process_id, event_type=EventTypes.PROC_CPU_DONE, event_time=self.time + rem_time)
