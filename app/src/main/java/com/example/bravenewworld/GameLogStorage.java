//package com.example.bravenewworld;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class GameLogStorage {
//
//    private static final String PREFS_NAME = "GameLogStorage";
//
//    public static void saveLog(Context context, long date, String log) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(String.valueOf(date), log);
//        editor.apply();
//    }
//
//    public static String getLog(Context context, long date) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        return prefs.getString(String.valueOf(date), null);
//    }
//
//    public static void deleteLog(Context context, long date) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.remove(String.valueOf(date));
//        editor.apply();
//    }
//}

package com.example.bravenewworld;

import android.content.Context;
import android.content.SharedPreferences;

/** 데이터 저장 위치
 SharedPreferences 파일은 Android 디바이스의 내부 저장소에 저장됩니다. 이 파일은 앱의 데이터 디렉토리에 위치하며, 다른 앱이 접근할 수 없습니다.
 경로 예시: /data/data/<패키지 이름>/shared_prefs/GameLogStorage.xml
 */
public class GameLogStorage {

    // SharedPreferences 파일의 이름을 GameLogStorage로 정의합니다. 이 파일은 내부 저장소에 저장됩니다.
    private static final String PREFS_NAME = "GameLogStorage";

    /**
     * 로그를 저장하는 메서드
     *
     * @param context 현재 컨텍스트
     * @param date    저장할 날짜 (밀리초 단위)
     * @param log     저장할 로그 내용
     */
    public static void saveLog(Context context, long date, String log) {
        // SharedPreferences 객체를 PREFS_NAME으로 가져옵니다.
        // Context.MODE_PRIVATE은 이 파일이 해당 앱에서만 접근 가능하도록 설정합니다.
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // SharedPreferences의 Editor 객체를 가져옵니다. Editor 객체는 데이터를 추가, 수정 또는 삭제할 수 있습니다.
        SharedPreferences.Editor editor = prefs.edit();
        // date(키)와 log(값)를 저장합니다.
        editor.putString(String.valueOf(date), log);
        // 변경 사항을 저장합니다.
        editor.apply();
    }

    /**
     * 저장된 로그를 가져오는 메서드
     *
     * @param context 현재 컨텍스트
     * @param date    가져올 로그의 날짜 (밀리초 단위)
     * @return 해당 날짜에 저장된 로그, 없으면 null 반환
     */
    public static String getLog(Context context, long date) {
        // SharedPreferences를 엽니다.
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // date(키)에 해당하는 값을 반환합니다. 값이 없으면 null을 반환합니다.
        return prefs.getString(String.valueOf(date), null);
    }

    /**
     * 저장된 로그를 삭제하는 메서드
     *
     * @param context 현재 컨텍스트
     * @param date    삭제할 로그의 날짜 (밀리초 단위)
     */
    public static void deleteLog(Context context, long date) {
        // SharedPreferences를 엽니다.
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // SharedPreferences의 Editor 객체를 가져옵니다.
        SharedPreferences.Editor editor = prefs.edit();
        // date(키)에 해당하는 값을 제거합니다.
        editor.remove(String.valueOf(date));
        // 변경 사항을 저장합니다.
        editor.apply();
    }
}
