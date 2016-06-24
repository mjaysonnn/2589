<<<<<<< HEAD
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
=======
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
     * 4 4 LLLL LWLW LLLW LWWW
     */
    public static char[][] island;

    public static ArrayList<Integer> x = new ArrayList<Integer>();
    public static ArrayList<Integer> y = new ArrayList<Integer>();
    public static ArrayList<Integer> l = new ArrayList<Integer>();
    public static int N;
    public static int M;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        island = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            island[i] = s.toCharArray();
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                x.clear();
                y.clear();
                l.clear();
                if (island[i][j] == 'L') {
                    // (i, j)의 모든 좌표에 대해 최단 경로를 구한뒤
                    // 그 값의 최대치를 구한다.
                    max = Math.max(max, bfs(i, j));
                }else continue;
            }
        }
        System.out.println(max);

    }

    public static void addQueue(int i, int j, int k) {
        
        // 이미 큐에 들어가 있는 좌표는 포함시키지 않는다.
        boolean flag = false;
        
        for (int l = 0; l < x.size(); l++) {
            if (x.get(l).intValue() == i
            &&  y.get(l).intValue() == j){
                flag = true;
                break;
            }
        }
        
        if(!flag){
            x.add(i);
            y.add(j);
            l.add(k);
        }
    }
    
    public static int bfs(int i, int j) {
        
        int pos = 0; // 큐를 가리키는 포지션
        int result = 0;
        
        addQueue(i, j, 0); // 첫 좌표를 큐에 넣는다.

        // 큐가 전부 빌때까지 루프를 돌린다.
        while (x.size() != 0 ) {
            
            island[x.get(pos)][y.get(pos)] = ' ';   // 현재 가리키는 위치의 값을 공백으로 바꿔줘 중복체크 안하게끔 처리.

            // 검사하는 좌표의 위치가 진행 가능한 경우('L') 큐에 해당 좌표를 추가한다.
            // 좌측
            if (x.get(pos) > 0 && island[x.get(pos) - 1][y.get(pos)] == 'L') {
                addQueue(x.get(pos) - 1, y.get(pos), l.get(pos) + 1);
            }
            // 우측
            if (x.get(pos) < N - 1 && island[x.get(pos) + 1][y.get(pos)] == 'L') {
                addQueue(x.get(pos) + 1, y.get(pos), l.get(pos) + 1);
            }
            // 상단
            if (y.get(pos) > 0 && island[x.get(pos)][y.get(pos) - 1] == 'L') {
                addQueue(x.get(pos), y.get(pos) - 1, l.get(pos) + 1);
            }
            // 하단
            if (y.get(pos) < M - 1 && island[x.get(pos)][y.get(pos) + 1] == 'L') {
                addQueue(x.get(pos), y.get(pos) + 1, l.get(pos) + 1);
            }
            
            // 현재 위치의 값을 결과값으로 갖고 있고, 맨 마지막에 남겨진 값이 최종값이 된다.
            result = l.get(pos);
            // 현재 위치를 큐에서 삭제한다.
            // 삭제하고 나면 현재 위치에서 갈 수 있었던 좌표들이 남게 되고, 해당 좌표들을 기준으로 다시 while 루프를 실행
            x.remove(pos);
            y.remove(pos);
            l.remove(pos);
        }

        // 변경된 값들. 지나온 값들에 대해 다시 'L'로 변경해준다.
        for (int ii = 0; ii < N; ii++) {
            for (int jj = 0; jj < M; jj++) {
                if(island[ii][jj] == ' ')
                    island[ii][jj] = 'L';
            }
        }
        
        // 최종값을 반환. 즉, 더 이상 연결 된 곳이 없는 마지막 위치에 도달했을 때의 이동 거리를 반환한다.
        return result;
>>>>>>> branch 'master' of https://github.com/mjaysonnn/2589.git
    }
}