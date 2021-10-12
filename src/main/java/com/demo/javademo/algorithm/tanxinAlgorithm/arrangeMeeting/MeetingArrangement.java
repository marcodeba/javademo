package com.demo.javademo.algorithm.tanxinAlgorithm.arrangeMeeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingArrangement {
    public static void main(String[] args) {
        Meeting[] meetings = initMeetings();
        // 对会议进行排序，按照会议结束时间升序，如果会议结束时间一样，则按照会议开始时间降序
        Arrays.sort(meetings);
        // 记录参加的会议
        List<Meeting> attendMeetings = new ArrayList<>(meetings.length);
        // 用于记录上次会议结束时间，下场会议开始时间必须晚于 lastEnd
        int lastEnd = 0;
        for (Meeting meeting : meetings) {
            if (meeting.getStart() > lastEnd) {
                attendMeetings.add(meeting);
                lastEnd = meeting.getEnd();
            }
        }

        System.out.println("最多一共可以参加" + attendMeetings.size() + "场会议");
        for (Meeting attendMeeting : attendMeetings) {
            System.out.println(attendMeeting);
        }
    }

    public static Meeting[] initMeetings() {
        System.out.print("请输入会议总数：");
        Scanner input = new Scanner(System.in);
        int totalMeetings = input.nextInt();
        Meeting[] meetings = new Meeting[totalMeetings];
        for (int i = 0; i < totalMeetings; i++) {
            System.out.println("请输入第" + (i + 1) + "个会议开始时间");
            int begin = input.nextInt();
            System.out.println("请输入第" + (i + 1) + "个会议结束时间");
            int end = input.nextInt();
            meetings[i] = new Meeting(i + 1, begin, end);
        }
        return meetings;
    }
}

class Meeting implements Comparable<Meeting> {
    private int id;
    private int start;
    private int end;

    public Meeting(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    // 按照会议结束时间升序，会议结束时间相同，则按会议开始时间做降序排列
    @Override
    public int compareTo(Meeting o) {
        if (o.end > this.end) {
            return -1;
        } else if (o.end < this.end) {
            return 1;
        } else {
            // 会议结束时间相同，则按会议开始时间做降序排列
            return (o.start < this.start) ? 1 : 0;
        }
    }
}