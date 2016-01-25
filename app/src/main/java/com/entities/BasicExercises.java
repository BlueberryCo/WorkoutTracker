package com.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boiko on 12/1/2015.
 */
public class BasicExercises {

    //Exercises categories constants
    public static final int BARBELL_MOVES = 1;
    public static final int GYMNASTIC_MOVES = 2;
    public static final int DUMBBELL_MOVES = 3;
    public static final int OLYMPIC_MOVES = 4;
    public static final int CARDIO_MOVES = 5;
    public static final int OTHER = 6;

    //Exercises constants
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
    public static final int THRUSTER = 30;
    public static final int WALL_BALL = 31;

    public static Map<Integer, BasicExerciseInfo> getExercisesMap(){
        Map<Integer, BasicExerciseInfo> result = new HashMap<>(31);

        result.put(BACK_SQUAT, new BasicExerciseInfo("Клек", "Back squat", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(FRONT_SQUAT, new BasicExerciseInfo("Преден клек", "Front squat", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(DEADLIFT, new BasicExerciseInfo("Мъртва тяга", "Deadlift", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(BENCH_PRESS, new BasicExerciseInfo("Лег", "Bench press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(SHOULDER_PRESS, new BasicExerciseInfo("Раменна преса", "Shoulder press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(POWER_CLEAN, new BasicExerciseInfo("Power clean", "Power clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SQUAT_CLEAN, new BasicExerciseInfo("Squat clean", "Squat clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(HANG_CLEAN, new BasicExerciseInfo("Hang clean", "Hang clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SQUAT_SNATCH, new BasicExerciseInfo("Squat snatch", "Squat snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(POWER_SNATCH, new BasicExerciseInfo("Power snatch", "Power snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(HANG_SNATCH, new BasicExerciseInfo("Hang snatch", "Hang snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SWING, new BasicExerciseInfo("Суинг", "Swing", getCategoriesMap().get(OTHER)));
        result.put(DUMBBELL_SNATCH, new BasicExerciseInfo("Dumbbell snatch", "Dumbbell snatch", getCategoriesMap().get(DUMBBELL_MOVES)));
        result.put(OVERHEAD_SQUAT, new BasicExerciseInfo("Клек с щанга над глава", "Overhead squat", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(PUSH_PRESS, new BasicExerciseInfo("Пуш преса", "Push press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(CLEAN_AND_JERK, new BasicExerciseInfo("Clean and jerk", "Clean and jerk", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(DIPS, new BasicExerciseInfo("Кофи", "Dips", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(PULL_UPS, new BasicExerciseInfo("Набирания", "Pull ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HIGH_PULLS, new BasicExerciseInfo("Високо теглене", "High pull", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(BOX_JUMPS, new BasicExerciseInfo("Скок върху платформа", "Box jump", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(BURPEES, new BasicExerciseInfo("Бърпи", "Burpee", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(PUSH_UPS, new BasicExerciseInfo("Лицеви опори", "Push ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(SIT_UPS, new BasicExerciseInfo("Коремни преси", "Sit ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(KNEES_TO_ELBOWS, new BasicExerciseInfo("Колене към лакти", "Knees to elbows", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(LUNGES, new BasicExerciseInfo("Напади", "Lunges", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(FARMER_CARRY, new BasicExerciseInfo("Фермерска раходка", "Farmer carry", getCategoriesMap().get(OTHER)));
        result.put(MUSCLE_UPS, new BasicExerciseInfo("Силови", "Muscle ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(BACK_SHOULDER_PRESS, new BasicExerciseInfo("Раменна преса зад врат", "Back shoulders press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(ABS_ROLLER, new BasicExerciseInfo("Колелце", "Abs roller", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(THRUSTER, new BasicExerciseInfo("Тръстер", "Thruster", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(WALL_BALL, new BasicExerciseInfo("Клек с топка с подхвърляне", "Wall ball", getCategoriesMap().get(OTHER)));

        return result;
    }

    public static final Map<Integer, CategoryInfo> getCategoriesMap(){
        Map<Integer, CategoryInfo> result = new HashMap<>(6);

        result.put(BARBELL_MOVES, new CategoryInfo("Основни движения", "Barbell moves"));
        result.put(GYMNASTIC_MOVES, new CategoryInfo("Упражнения със собствено тегло", "Gymnastic moves"));
        result.put(DUMBBELL_MOVES, new CategoryInfo("Упражнения с дъмбели", "Dumbbell moves"));
        result.put(OLYMPIC_MOVES, new CategoryInfo("Олимпииски движения", "Olympic moves"));
        result.put(CARDIO_MOVES, new CategoryInfo("Кардио движения", "Cardio moves"));
        result.put(OTHER, new CategoryInfo("Други", "Other"));

        return result;
    }
}
