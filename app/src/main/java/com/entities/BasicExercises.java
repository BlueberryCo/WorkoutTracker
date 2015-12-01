package com.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boiko on 12/1/2015.
 */
public class BasicExercises {

    public static final int BACK_SQUAT = 1;
    public static final int FRONT_SQUAT = 2;
    public static final int DEADLIFT = 3;
    public static final int BENCH_PRESS = 4;
    public static final int SHOULDER_PRESS = 5;
    public static final int POWER_CLEAN = 6;
    public static final int SQUAT_CLEAN = 7;
    public static final int HANG_CLEAN = 8;
    public static final int SQUAT_SNATCH = 9;
    public static final int POWER_SNATCH = 10;
    public static final int HANG_SNATCH = 11;
    public static final int SWING = 12;
    public static final int DUMBBELL_SNATCH = 13;
    public static final int OVERHEAD_SQUAT = 14;
    public static final int PUSH_PRESS = 15;
    public static final int CLEAN_AND_JERK = 16;
    public static final int DIPS = 17;
    public static final int PULL_UPS = 18;
    public static final int HIGH_PULLS = 19;
    public static final int BOX_JUMPS = 20;
    public static final int BURPEES = 21;
    public static final int PUSH_UPS = 22;
    public static final int SIT_UPS = 23;
    public static final int KNEES_TO_ELBOWS = 24;
    public static final int LUNGES = 25;
    public static final int FARMER_CARRY = 26;
    public static final int MUSCLE_UPS = 27;
    public static final int BACK_SHOULDER_PRESS = 28;
    public static final int ABS_ROLLER = 29;
    public static final int TRUSTER = 30;
    public static final int WALL_BALL = 31;

    public static Map<Integer, String> getExercisesMap(){
        Map<Integer, String> result = new HashMap<>(31);

        result.put(BACK_SQUAT, "Клек");
        result.put(FRONT_SQUAT, "Преден клек");
        result.put(DEADLIFT, "Мъртва тяга");
        result.put(BENCH_PRESS, "Лег");
        result.put(SHOULDER_PRESS, "Раменна преса");
        result.put(POWER_CLEAN, "Power clean");
        result.put(SQUAT_CLEAN, "Squat clean");
        result.put(HANG_CLEAN, "Hang clean");
        result.put(SQUAT_SNATCH, "Squat snatch");
        result.put(POWER_SNATCH, "Power snatch");
        result.put(HANG_SNATCH, "Hang snatch");
        result.put(SWING, "Суинг");
        result.put(DUMBBELL_SNATCH, "Dumbbell snatch");
        result.put(OVERHEAD_SQUAT, "Клек с щанга над глава");
        result.put(PUSH_PRESS, "Пуш преса");
        result.put(CLEAN_AND_JERK, "Clean and jerk");
        result.put(DIPS, "Кофи");
        result.put(PULL_UPS, "Набирания");
        result.put(HIGH_PULLS, "Високо теглене");
        result.put(BOX_JUMPS, "Скок върху платформа");
        result.put(BURPEES, "Бърпи");
        result.put(PUSH_UPS, "Лицеви опори");
        result.put(SIT_UPS, "Коремни преси");
        result.put(KNEES_TO_ELBOWS, "Колене към лакти");
        result.put(LUNGES, "Напади");
        result.put(FARMER_CARRY, "Фермерска раходка");
        result.put(MUSCLE_UPS, "Силови");
        result.put(BACK_SHOULDER_PRESS, "Раменна преса зад врат");
        result.put(ABS_ROLLER, "Колелце");
        result.put(TRUSTER, "Тръстер");
        result.put(WALL_BALL, "Клек с топка с подхвърляне");

        return result;
    }
}
