class Solution(object):
    """
    Input a string
    """
    def lengthOfLongestSubstring(self, s):
        # Set up an empty map
        mapSet = {}
        # Set initial and result value to 0
        start, result = 0, 0
        # Sequence through each character in the string
        for end in range(len(s)):
            """
            Check if the currently inspected character ending         variation exists in the map.
            If it does:
                Compare the maximum between what is already in the dictionary and the start value.
            """
            if s[end] in mapSet:
                start = max(mapSet[s[end]], start)
            """
            If the ending substring is not already in the map, then compare between the result already existing and the currently inspected character sequence.
            Note: For the first character that is sequenced, the result will be 1 since <end - start + 1> equates to just <0-0 + 1>.
            Inside the map, insert the character ending as the key and map this to an increment of it.
            """
            result = max(result, end-start+1)
            mapSet[s[end]] = end+1
            
        print(result)
        return result

ans = Solution()
ans.lengthOfLongestSubstring("abcabcab")