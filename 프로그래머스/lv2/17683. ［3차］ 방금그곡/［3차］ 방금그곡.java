import java.io.*;
import java.util.*;

class Solution {
	// A B C D E F G
	// A# -> O
	// C# -> P
	// D# -> Q
	// F# -> R
	// G# -> S

	static class song {
		int num;
		int playTime;
		String songName;
		String code;

		public song(int num, int playTime, String songName, String fullCode) {
			this.num = num;
			this.playTime = playTime;
			this.songName = songName;
			this.code = fullCode;
		}
	}

	public String solution(String m, String[] musicinfos) {
		String answer = "";

		m = changeStr(m);
		song[] songArr = new song[musicinfos.length];

		for (int i = 0; i < musicinfos.length; i++) {
			String[] songInfos = musicinfos[i].split(",");

			String[] start = songInfos[0].split(":");
			String[] end = songInfos[1].split(":");

			String songName = songInfos[2];
			String code = songInfos[3];

			code = changeStr(code);

			int playTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) - Integer.parseInt(start[0]) * 60
					- Integer.parseInt(start[1]);

			String fullCode = "";
			int a = 0;
			int b = 0;
			while (a < playTime) {
				fullCode += code.charAt(b);
				if (b == code.length() - 1) {
					b = 0;
				} else {
					b++;
				}
				a++;
			}

			songArr[i] = new song(i + 1, playTime, songName, fullCode);
		}

		ArrayList<song> answerSongList = new ArrayList<>();
		for (int i = 0; i < songArr.length; i++) {
			if (songArr[i].code.contains(m)) {
				answerSongList.add(songArr[i]);
			}
		}

		if (answerSongList.size() == 0) {
			answer = "(None)";
		} else if (answerSongList.size() == 1) {
			answer = answerSongList.get(0).songName;
		} else {
			Collections.sort(answerSongList, (song s1, song s2) -> {
				if (s1.playTime != s2.playTime) {
					return s2.playTime - s1.playTime;
				} else {
					return s1.num - s2.num;
				}
			});

			answer = answerSongList.get(0).songName;
		}
		return answer;
	}

	public static String changeStr(String inputStr) {
		inputStr = inputStr.replace("A#", "O");
		inputStr = inputStr.replace("C#", "P");
		inputStr = inputStr.replace("D#", "Q");
		inputStr = inputStr.replace("F#", "R");
		inputStr = inputStr.replace("G#", "S");
        
        return inputStr;
	}
}