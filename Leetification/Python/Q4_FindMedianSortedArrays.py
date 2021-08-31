class Solution(object):
    """
    Take in two lists
    """
    def findMedianSortedArrays(self, nums1, nums2):
        # If list1 has more items than list2, perform a swap
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
        # Store the lengths of the lists in variables
        x, y = len(nums1), len(nums2)
        """
        Set the low (starting) value to be 0 and the high (maximmum) value to equal the length of list1 (which should be smaller than or equal to list2 in length at this point)
        """
        low, high = 0, x
        """
        Sequence from the starting index until the concluding index of the smaller list.
        Partition to give a left side and a right side.
        """
        while low <= high:
            partitionx = (low+high)/2
            partitiony = (x+y+1)/2 - partitionx


