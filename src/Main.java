import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
  
// 보물섬
public class Main {
  
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine().trim());
        int row=Integer.parseInt(st.nextToken());
        int col=Integer.parseInt(st.nextToken());
          
        boolean[][] map=new boolean[row+1][col+1];
        for(int r=1 ; r<=row ; r++){
            String s=br.readLine().trim();
            for(int c=1 ; c<=col ; c++){
                String a=s.substring(c-1, c);
                if(a.equals("L")) map[r][c]=true;
            }
        }// 입력 끝
          
        LinkedList<Integer> queueR=new LinkedList<>();
        LinkedList<Integer> queueC=new LinkedList<>();
        int maxD=0;
          
        // 육지인 지점 각각에서 출발하면서 가장 긴 최단거리가 있는지 확인해본다.
        for(int r=1 ; r<=row ; r++){
            for(int c=1 ; c<=col ; c++){
                if(map[r][c]){
                      
                    boolean[][] visit=new boolean[row+1][col+1];
                    queueR.add(r); queueC.add(c);
                    visit[r][c]=true;
                    int hour=0;
                      
                    while(true){
                        hour++;
                        int size=queueR.size();
                        for(int i=0 ; i<size ; i++){
                            int rr=queueR.pop();
                            int cc=queueC.pop();
                              
                            // 아래
                            if(rr+1<=row && !visit[rr+1][cc] && map[rr+1][cc]){
                                visit[rr+1][cc]=true;
                                queueR.add(rr+1);
                                queueC.add(cc);
                            }
                            // 오
                            if(cc+1<=col && !visit[rr][cc+1] && map[rr][cc+1]){
                                visit[rr][cc+1]=true;
                                queueR.add(rr);
                                queueC.add(cc+1);
                            }
                            // 위
                            if(rr-1>=1 && !visit[rr-1][cc] && map[rr-1][cc]){
                                visit[rr-1][cc]=true;
                                queueR.add(rr-1);
                                queueC.add(cc);
                            }
                            // 오
                            if(cc-1>=1 && !visit[rr][cc-1] && map[rr][cc-1]){
                                visit[rr][cc-1]=true;
                                queueR.add(rr);
                                queueC.add(cc-1);
                            }
                        }// for문 끝
                          
                        if(queueR.isEmpty()){
                            if(hour>maxD) maxD=hour;
                            break;
                        }
                    }
                      
                }// if문 끝
            }
        }
          
        System.out.println(maxD-1);
    }
}