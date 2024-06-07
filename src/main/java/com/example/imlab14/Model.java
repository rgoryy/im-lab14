package com.example.imlab14;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Model {

    private static Label timeLabel;

    private static List<Agent> agents = new ArrayList<>();

    private static double timeLimit = 5;

    private static double T = 0;

    private static Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                event -> process()
            ));

    public static void run() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void setTimeline(Duration duration) {
        timeline.stop();
        timeline = new Timeline(
                new KeyFrame(duration,
                        event -> process()
                ));
        run();
    }

    public static void process() {
        Agent activeAgent = null;
        double tMin = Double.MAX_VALUE;
        for (Agent agent : agents) {
            double ta = agent.getNextEventTime();
            if (ta < tMin) {
                tMin = ta;
                activeAgent = agent;
            }
        }

        double dif = tMin - T;
        T = tMin;

        if (activeAgent != null) {
            activeAgent.processEvent();
            timeLabel.setText(String.valueOf(T));
        }

        if (stopCondition(T, activeAgent)) {
            stopTimeline();
            return;
        }


        setTimeline(Duration.seconds(dif));
    }

    public static void stopTimeline() {
        timeline.stop();
    }

    private static boolean stopCondition(double t, Agent activeAgent) {
        return (t > timeLimit) || activeAgent == null;
    }

    public static double getTime() {
        return T;
    }

    public static void setAgents(List<Agent> agents) {
        Model.agents = agents;
    }

    public static void setAgentsAndTimeLabel(List<Agent> agents, Label timeLabel) {
        Model.agents = agents;
        Model.timeLabel = timeLabel;
    }

    public static void setTimeLimit(double timeLimit) {
        Model.timeLimit = timeLimit;
    }

    public static void setTimeLabel(Label timeLabel) {
        Model.timeLabel = timeLabel;
    }
}
