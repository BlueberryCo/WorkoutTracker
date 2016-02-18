package blueberryco.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by boiko on 12/1/2015.
 */
public class BasicExercises {

    //Exercises categories constants
    public static final int BARBELL_MOVES = 1;
    public static final int GYMNASTIC_MOVES = 2;
    public static final int OLYMPIC_MOVES = 3;
    public static final int CARDIO_MOVES = 4;
    public static final int OTHER = 5;

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
    public static final int HIGH_PULLS_SNATCH_GRIP = 32;
    public static final int PISTOLS = 33;
    public static final int HANDSTAND_PUSH_UPS = 34;
    public static final int INVERTED_ROW = 35;
    public static final int HANGING_KNEE_RAISE = 36;
    public static final int VERTICAL_LEGS_HIP_RAISE = 37;
    public static final int L_SIT = 38;
    public static final int PLANK = 39;
    public static final int LONG_JUMP = 40;
    public static final int PULLOVER = 41;
    public static final int DUMBBELL_ROW = 42;
    public static final int BARBELL_ROW = 43;
    public static final int ROMANIAN_DEADLIFT = 44;

    public static Map<Integer, BasicExerciseInfo> getExercisesMap(){
        Map<Integer, BasicExerciseInfo> result = new HashMap<>(44);

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
        result.put(SWING, new BasicExerciseInfo("Суинг", "Swing", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(DUMBBELL_SNATCH, new BasicExerciseInfo("Dumbbell snatch", "Dumbbell snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(OVERHEAD_SQUAT, new BasicExerciseInfo("Клек с щанга над глава", "Overhead squat", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(PUSH_PRESS, new BasicExerciseInfo("Пуш преса", "Push press", getCategoriesMap().get(OTHER)));
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
        result.put(FARMER_CARRY, new BasicExerciseInfo("Фермерска раходка", "Farmer carry", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(MUSCLE_UPS, new BasicExerciseInfo("Силови", "Muscle ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(BACK_SHOULDER_PRESS, new BasicExerciseInfo("Раменна преса зад врат", "Back shoulders press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(ABS_ROLLER, new BasicExerciseInfo("Колелце", "Abs roller", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(THRUSTER, new BasicExerciseInfo("Тръстер", "Thruster", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(WALL_BALL, new BasicExerciseInfo("Клек с топка с подхвърляне", "Wall ball", getCategoriesMap().get(OTHER)));
        result.put(HIGH_PULLS_SNATCH_GRIP, new BasicExerciseInfo("High pull snatch grip", "High pull snatch grip", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(PISTOLS, new BasicExerciseInfo("Пистолети", "Pistols", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HANDSTAND_PUSH_UPS, new BasicExerciseInfo("Преси от стойка на ръце", "Handstand push ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(INVERTED_ROW, new BasicExerciseInfo("Обратно гребане", "Inverted row", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HANGING_KNEE_RAISE, new BasicExerciseInfo("Повдигане на краката от вис", "Hanging knee raise", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(VERTICAL_LEGS_HIP_RAISE, new BasicExerciseInfo("Повдигане на краката на успоредка", "Vertical legs-hip raise", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(L_SIT, new BasicExerciseInfo("L-стойка", "L-sit", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(PLANK, new BasicExerciseInfo("Планк", "Plank", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(LONG_JUMP, new BasicExerciseInfo("Скок на дължина", "Long jump", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(PULLOVER, new BasicExerciseInfo("Пуловър", "Pullover", getCategoriesMap().get(OTHER)));
        result.put(DUMBBELL_ROW, new BasicExerciseInfo("Гребане с дъмбел", "Dumbbell row", getCategoriesMap().get(OTHER)));
        result.put(BARBELL_ROW, new BasicExerciseInfo("Гребане с щанга", "Barbell row", getCategoriesMap().get(OTHER)));
        result.put(ROMANIAN_DEADLIFT, new BasicExerciseInfo("Римска мъртва тяга", "Romanian deadlift", getCategoriesMap().get(OTHER)));

        return result;
    }

    public static Map<Integer, CategoryInfo> getCategoriesMap(){
        Map<Integer, CategoryInfo> result = new HashMap<>(5);

        result.put(BARBELL_MOVES, new CategoryInfo(BARBELL_MOVES, "Базови с щанга", "Barbell moves"));
        result.put(GYMNASTIC_MOVES, new CategoryInfo(GYMNASTIC_MOVES, "Базови със собствено тегло", "Bodyweight  moves"));
        result.put(OLYMPIC_MOVES, new CategoryInfo(OLYMPIC_MOVES, "Олимпииски движения", "Olympic moves"));
        result.put(CARDIO_MOVES, new CategoryInfo(CARDIO_MOVES, "Метаболитни", "Cardio moves"));
        result.put(OTHER, new CategoryInfo(OTHER, "Други", "Other"));

        return result;
    }

    public static List<CategoryInfo> getAllCategories(){

        List<CategoryInfo> result = new ArrayList<>();

        for(Map.Entry<Integer, CategoryInfo> entry : getCategoriesMap().entrySet()){
            result.add(entry.getValue());
        }

        return result;
    }

    public static List<BasicExerciseInfo> getExercisesForCategory(int category){

        List<BasicExerciseInfo> result = new ArrayList<>();

        for(Map.Entry<Integer, BasicExerciseInfo> entry : getExercisesMap().entrySet()){
            if(entry.getValue().getCategory().getId() == category){
                result.add(entry.getValue());
            }
        }

        return result;
    }
}
