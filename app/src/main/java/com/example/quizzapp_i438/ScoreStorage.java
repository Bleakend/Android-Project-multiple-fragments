    package com.example.quizzapp_i438;

    import android.content.Context;
    import android.content.SharedPreferences;

    public class ScoreStorage {

        private static final String PREFS_NAME = "results";
        private static final String KEY_NB_ATTEMPTS = "nb_attempts";
        private SharedPreferences prefs;
        private SharedPreferences.Editor editor;


        public ScoreStorage(Context context){
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            editor = prefs.edit();
        }
        public void saveQuizScore(int correctAnswers) {
            int nbAttempts = prefs.getInt(KEY_NB_ATTEMPTS, 0);
            nbAttempts++;

            editor.putInt(String.valueOf(nbAttempts), correctAnswers);
            editor.putInt("nb_attempts", nbAttempts);
            editor.commit();
        }

        public int[] getAllScores() {
            int nbAttempts = prefs.getInt(KEY_NB_ATTEMPTS, 0);
            int[] scores = new int[nbAttempts];

            for (int i = 1; i <= nbAttempts; i++) {
                scores[i - 1] = prefs.getInt(String.valueOf(i), 0);
            }

            return scores;
        }

        public void clearSharedPreference() {
            editor.clear();
            editor.commit();
        }

    }
