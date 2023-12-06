import java.util.*;

class Song{
    int num, play;
    public Song(int n, int p){
        num = n; 
        play = p;
    }
}
class Solution {
    public int[] solution(String[] genres, int[] plays) {        
        HashMap<String, ArrayList<Song>> genreMap = new HashMap<>();
        HashMap<String, Integer> genreCntMap = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            int play = plays[i];
            String genre = genres[i];
            
            genreCntMap.put(genre, genreCntMap.getOrDefault(genre, 0) + play);
            genreMap.putIfAbsent(genre, new ArrayList<Song>());
            genreMap.get(genre).add(new Song(i, play));
        }
        
        for(String genre : genreMap.keySet()){
            genreMap.get(genre).sort((s1, s2) -> s2.play - s1.play);;
        }
        
        ArrayList<String> sortedGenre = new ArrayList<>(genreCntMap.keySet()); 
        sortedGenre.sort((p1, p2) -> genreCntMap.get(p2) - genreCntMap.get(p1));
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        for(String genre : sortedGenre){
            if(genreMap.get(genre).size() == 1){
                answerList.add(genreMap.get(genre).get(0).num);
            }else if(genreMap.get(genre).size() >= 2){
                answerList.add(genreMap.get(genre).get(0).num);
                answerList.add(genreMap.get(genre).get(1).num);
            }
        }
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}