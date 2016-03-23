package blueberryco.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by boiko on 12/1/2015.
 */
public class BasicExercises {

    private BasicExercises(){
        throw new AssertionError("Static class constructor call.");
    }

    //Exercises categories constants
    public static final int BARBELL_MOVES = 1;
    public static final int GYMNASTIC_MOVES = 2;
    public static final int OLYMPIC_MOVES = 3;
    public static final int CARDIO_MOVES = 4;
    public static final int OTHER = 5;
    //Complex
    public static final int CROSSFIT_WORKOUTS = 6;

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
    //Complex
    public static final int TREE_ROUNDS = 45;
    public static final int FIVE_ROUNDS = 46;
    public static final int SEVEN_ROUNDS = 47;
    public static final int TEN_ROUNDS = 48;
    public static final int FROM_ONE_TO_TEN = 49;
    public static final int FROM_TEN_TO_ONE = 50;
    public static final int AMRAP = 51; //As many reps/rounds as possible
    public static final int EMOM = 52; //Every minute on the minute
    public static final int FOR_TIME = 53;
    public static final int TABATA = 54;

    public static Map<Integer, BasicExerciseInfo> getExercisesMap(){
        Map<Integer, BasicExerciseInfo> result = new HashMap<>(54);

        result.put(BACK_SQUAT, new BasicExerciseInfo(BACK_SQUAT, "Клек", "Back squat", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(FRONT_SQUAT, new BasicExerciseInfo(FRONT_SQUAT, "Преден клек", "Front squat", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(DEADLIFT, new BasicExerciseInfo(DEADLIFT, "Мъртва тяга", "Deadlift", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(BENCH_PRESS, new BasicExerciseInfo(BENCH_PRESS, "Лег", "Bench press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(SHOULDER_PRESS, new BasicExerciseInfo(SHOULDER_PRESS, "Раменна преса", "Shoulder press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(POWER_CLEAN, new BasicExerciseInfo(POWER_CLEAN, "Power clean", "Power clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SQUAT_CLEAN, new BasicExerciseInfo(SQUAT_CLEAN, "Squat clean", "Squat clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(HANG_CLEAN, new BasicExerciseInfo(HANG_CLEAN, "Hang clean", "Hang clean", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SQUAT_SNATCH, new BasicExerciseInfo(SQUAT_SNATCH, "Squat snatch", "Squat snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(POWER_SNATCH, new BasicExerciseInfo(POWER_SNATCH, "Power snatch", "Power snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(HANG_SNATCH, new BasicExerciseInfo(HANG_SNATCH, "Hang snatch", "Hang snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(SWING, new BasicExerciseInfo(SWING, "Суинг", "Swing", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(DUMBBELL_SNATCH, new BasicExerciseInfo(DUMBBELL_SNATCH, "Dumbbell snatch", "Dumbbell snatch", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(OVERHEAD_SQUAT, new BasicExerciseInfo(OVERHEAD_SQUAT, "Клек с щанга над глава", "Overhead squat", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(PUSH_PRESS, new BasicExerciseInfo(PUSH_PRESS, "Пуш преса", "Push press", getCategoriesMap().get(OTHER)));
        result.put(CLEAN_AND_JERK, new BasicExerciseInfo(CLEAN_AND_JERK, "Clean and jerk", "Clean and jerk", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(DIPS, new BasicExerciseInfo(DIPS, "Кофи", "Dips", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(PULL_UPS, new BasicExerciseInfo(PULL_UPS, "Набирания", "Pull ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HIGH_PULLS, new BasicExerciseInfo(HIGH_PULLS, "Високо теглене", "High pull", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(BOX_JUMPS, new BasicExerciseInfo(BOX_JUMPS, "Скок върху платформа", "Box jump", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(BURPEES, new BasicExerciseInfo(BURPEES, "Бърпи", "Burpee", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(PUSH_UPS, new BasicExerciseInfo(PUSH_UPS, "Лицеви опори", "Push ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(SIT_UPS, new BasicExerciseInfo(SIT_UPS, "Коремни преси", "Sit ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(KNEES_TO_ELBOWS, new BasicExerciseInfo(KNEES_TO_ELBOWS, "Колене към лакти", "Knees to elbows", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(LUNGES, new BasicExerciseInfo(LUNGES, "Напади", "Lunges", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(FARMER_CARRY, new BasicExerciseInfo(FARMER_CARRY, "Фермерска раходка", "Farmer carry", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(MUSCLE_UPS, new BasicExerciseInfo(MUSCLE_UPS, "Силови", "Muscle ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(BACK_SHOULDER_PRESS, new BasicExerciseInfo(BACK_SHOULDER_PRESS, "Раменна преса зад врат", "Back shoulders press", getCategoriesMap().get(BARBELL_MOVES)));
        result.put(ABS_ROLLER, new BasicExerciseInfo(ABS_ROLLER, "Колелце", "Abs roller", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(THRUSTER, new BasicExerciseInfo(THRUSTER, "Тръстер", "Thruster", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(WALL_BALL, new BasicExerciseInfo(WALL_BALL, "Клек с топка с подхвърляне", "Wall ball", getCategoriesMap().get(OTHER)));
        result.put(HIGH_PULLS_SNATCH_GRIP, new BasicExerciseInfo(HIGH_PULLS_SNATCH_GRIP, "High pull snatch grip", "High pull snatch grip", getCategoriesMap().get(OLYMPIC_MOVES)));
        result.put(PISTOLS, new BasicExerciseInfo(PISTOLS, "Пистолети", "Pistols", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HANDSTAND_PUSH_UPS, new BasicExerciseInfo(HANDSTAND_PUSH_UPS, "Преси от стойка на ръце", "Handstand push ups", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(INVERTED_ROW, new BasicExerciseInfo(INVERTED_ROW, "Обратно гребане", "Inverted row", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(HANGING_KNEE_RAISE, new BasicExerciseInfo(HANGING_KNEE_RAISE, "Повдигане на краката от вис", "Hanging knee raise", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(VERTICAL_LEGS_HIP_RAISE, new BasicExerciseInfo(VERTICAL_LEGS_HIP_RAISE, "Повдигане на краката на успоредка", "Vertical legs-hip raise", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(L_SIT, new BasicExerciseInfo(L_SIT, "L-стойка", "L-sit", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(PLANK, new BasicExerciseInfo(PLANK, "Планк", "Plank", getCategoriesMap().get(GYMNASTIC_MOVES)));
        result.put(LONG_JUMP, new BasicExerciseInfo(LONG_JUMP, "Скок на дължина", "Long jump", getCategoriesMap().get(CARDIO_MOVES)));
        result.put(PULLOVER, new BasicExerciseInfo(PULLOVER, "Пуловър", "Pullover", getCategoriesMap().get(OTHER)));
        result.put(DUMBBELL_ROW, new BasicExerciseInfo(DUMBBELL_ROW, "Гребане с дъмбел", "Dumbbell row", getCategoriesMap().get(OTHER)));
        result.put(BARBELL_ROW, new BasicExerciseInfo(BARBELL_ROW, "Гребане с щанга", "Barbell row", getCategoriesMap().get(OTHER)));
        result.put(ROMANIAN_DEADLIFT, new BasicExerciseInfo(ROMANIAN_DEADLIFT, "Римска мъртва тяга", "Romanian deadlift", getCategoriesMap().get(OTHER)));
        //Complex
        result.put(TREE_ROUNDS, new BasicExerciseInfo(TREE_ROUNDS, "3 рунда от", "3 rounds of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(FIVE_ROUNDS, new BasicExerciseInfo(FIVE_ROUNDS, "5 рунда от", "5 rounds of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(SEVEN_ROUNDS, new BasicExerciseInfo(SEVEN_ROUNDS, "7 рунда от", "7 rounds of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(TEN_ROUNDS, new BasicExerciseInfo(TEN_ROUNDS, "10 рунда от", "10 rounds of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(FROM_ONE_TO_TEN, new BasicExerciseInfo(FROM_ONE_TO_TEN, "От 1 до 10 от", "From 1 to 10 of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(FROM_TEN_TO_ONE, new BasicExerciseInfo(FROM_TEN_TO_ONE, "От 10 до 1 от", "From 10 to 1 of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(AMRAP, new BasicExerciseInfo(AMRAP, "Възможно повече рундове за {} минути от", "Complete as many rounds as possible in {} minutes of", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(EMOM, new BasicExerciseInfo(EMOM, "Всяка минута в рамките на минутата за {} минути", "Every minute on the minute for {} minutes", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(FOR_TIME, new BasicExerciseInfo(FOR_TIME, "За време", "For time", getCategoriesMap().get(CROSSFIT_WORKOUTS)));
        result.put(TABATA, new BasicExerciseInfo(TABATA, "Табата", "Tabata", getCategoriesMap().get(CROSSFIT_WORKOUTS)));

        return result;
    }

    public static Map<Integer, CategoryInfo> getCategoriesMap(){
        Map<Integer, CategoryInfo> result = new HashMap<>(6);

        result.put(BARBELL_MOVES, new CategoryInfo(BARBELL_MOVES, "Базови с щанга", "Barbell moves"));
        result.put(GYMNASTIC_MOVES, new CategoryInfo(GYMNASTIC_MOVES, "Базови със собствено тегло", "Bodyweight  moves"));
        result.put(OLYMPIC_MOVES, new CategoryInfo(OLYMPIC_MOVES, "Олимпииски движения", "Olympic moves"));
        result.put(CARDIO_MOVES, new CategoryInfo(CARDIO_MOVES, "Метаболитни", "Cardio moves"));
        result.put(OTHER, new CategoryInfo(OTHER, "Други", "Other"));
        //Complex
        result.put(CROSSFIT_WORKOUTS, new CategoryInfo(CROSSFIT_WORKOUTS, "Комплекси", "CrossFit workouts"));

        return result;
    }

    /**
     * Return all categories including category complexes.
     * @return Return all categories including category complexes.
     */
    public static List<CategoryInfo> getAllCategories(){

        List<CategoryInfo> result = new ArrayList<>();

        for(Map.Entry<Integer, CategoryInfo> entry : getCategoriesMap().entrySet()){
            result.add(entry.getValue());
        }

        Collections.sort(result);

        return result;
    }

    /**
     * Return all exercises categories excluding category complexes.
     * @return Return all exercises categories excluding category complexes.
     */
    public static List<CategoryInfo> getAllExercisesCategories(){
        List<CategoryInfo> result = getAllCategories();

        result.remove(getCategoriesMap().get(CROSSFIT_WORKOUTS));

        return result;
    }

    public static List<BasicExerciseInfo> getExercisesForCategory(int category){

        List<BasicExerciseInfo> result = new ArrayList<>();

        for(Map.Entry<Integer, BasicExerciseInfo> entry : getExercisesMap().entrySet()){
            if(entry.getValue().getCategory().getId() == category){
                result.add(entry.getValue());
            }
        }

        Collections.sort(result);

        return result;
    }

    private static final String MINUTES_SIGN = "{}";

    public static BasicExerciseInfo constructCrossfitWorkout(BasicExerciseInfo basicExerciseInfo, int rounds){
        basicExerciseInfo.setBgTranslation(basicExerciseInfo.getBgTranslation().replace(MINUTES_SIGN, String.valueOf(rounds)));
        basicExerciseInfo.setEnTranslation(basicExerciseInfo.getEnTranslation().replace(MINUTES_SIGN, String.valueOf(rounds)));

        return basicExerciseInfo;
    }

    public static boolean shouldConstructCrossfitWorkout(BasicExerciseInfo basicExerciseInfo){
        List<Integer> trueCollection = new ArrayList<>();

        trueCollection.add(AMRAP);
        trueCollection.add(EMOM);

        return trueCollection.contains(basicExerciseInfo.getId());
    }
}
