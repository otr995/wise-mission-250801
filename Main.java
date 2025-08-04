package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static class Wisesaying {
        int idx;
        String wise;
        String author;

        Wisesaying(int idx, String wise, String author) {
            this.idx = idx;
            this.wise = wise;
            this.author = author;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Wisesaying> sayings = new ArrayList<>();
        int nextId = 1;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                break;

            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String wise = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                sayings.add(new Wisesaying(nextId, wise, author));
                System.out.printf("%d번 명언이 등록되었습니다.\n", nextId++);
            }

            else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = sayings.size() - 1; i >= 0; i--) {
                    Wisesaying s = sayings.get(i);
                    System.out.printf("%d / %s / %s\n", s.idx, s.author, s.wise);
                }
            }

            else if (command.startsWith("삭제?id=")) {
                try {
                    int id = Integer.parseInt(command.split("=")[1]);
                    Wisesaying target = findId(sayings, id);
                    if (target != null) {
                        sayings.remove(target);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
                    } else {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
                    }
                } catch (Exception e) {
                    System.out.println("명령 형식이 잘못되었습니다. (예: 삭제?id=1)");
                }
            }

            else if (command.startsWith("수정?id=")) {
                try {
                    int id = Integer.parseInt(command.split("=")[1]);
                    Wisesaying target = findId(sayings, id);
                    if (target != null) {
                        System.out.printf("명언(기존) : %s\n", target.wise);
                        System.out.print("명언 : ");
                        target.wise = sc.nextLine();

                        System.out.printf("작가(기존) : %s\n", target.author);
                        System.out.print("작가 : ");
                        target.author = sc.nextLine();

                        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
                    } else {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
                    }
                } catch (Exception e) {
                    System.out.println("명령 형식이 잘못되었습니다. (예: 수정?id=1)");
                }
            }

            else {
                System.out.println("지원하지 않는 명령입니다.");
            }
        }
    }

    private static Wisesaying findId(ArrayList<Wisesaying> list, int id) {
        for (Wisesaying s : list) {
            if (s.idx == id) return s;
        }
        return null;
    }
}
