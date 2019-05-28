import java.util.ArrayList;
import java.util.List;

/*
 * Problem : Encode and Decode Strings
 * 
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}

Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}

So Machine 1 does:
string encoded_string = encode(strs);

and Machine 2 does:
vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.

 */
public class EncodeAndDecodeStrings {

	/*
	 * Solution: It does not matter at all if there is any / in the string, or
	 * any combinations of number with slash. In fact, we can use anything to
	 * replace the /, as long as it is not a number. Just try to replace it with
	 * 'a'.
	 * 
	 * It is because the slash here is just used as a separator, between the
	 * length and the actually string. It is act as a boundary to show where the
	 * length string ends, when the length has multiple digits. If we know all
	 * substring has less than 10 length, we in fact don't have to use any
	 * separator like /.
	 * 
	 * Example: Input aa2/bb will be encoded as "6/aa2/bb". When decoding, it finds
	 * a string of length of 6 which includes "aa2/bb" so it won't be able to
	 * read the "2/" as you might be thinking of.
	 */
	class Codec {

		// Encodes a list of strings to a single string.
		public String encode(List<String> strs) {
			StringBuilder sb = new StringBuilder();
			for (String s : strs) {
				sb.append(s.length()).append('/').append(s);
			}
			return sb.toString();
		}

		// Decodes a single string to a list of strings.
		public List<String> decode(String s) {
			List<String> ret = new ArrayList<String>();
			int i = 0;
			while (i < s.length()) {
				int slash = s.indexOf('/', i);
				int size = Integer.valueOf(s.substring(i, slash));
				i = slash + size + 1;
				ret.add(s.substring(slash + 1, i));
			}
			return ret;
		}
	}
}
