//package com.example.bravenewworld;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CalendarView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class CalendarActivity extends AppCompatActivity {
//
//    private CalendarView calendarView;
//    private Button deleteButton; // 추가된 부분
//    private TextView logTextView;
//    private long selectedDateMillis; // 추가된 부분
//    private Button backButton;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);
//
//        calendarView = findViewById(R.id.calendarView);
//        deleteButton = findViewById(R.id.buttonDelete); // 삭제 버튼 연결
//        logTextView = findViewById(R.id.logTextView);
//        backButton = findViewById(R.id.buttonBack);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                selectedDateMillis = new java.util.GregorianCalendar(year, month, dayOfMonth).getTimeInMillis();
//                String log = GameLogStorage.getLog(CalendarActivity.this, selectedDateMillis);
//                if (log != null) {
//                    logTextView.setText(log);
//                } else {
//                    logTextView.setText("No log for this date");
//                }
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedDateMillis > 0) {
//                    GameLogStorage.deleteLog(CalendarActivity.this, selectedDateMillis);
//                    logTextView.setText("No log for this date");
//                    Toast.makeText(CalendarActivity.this, "로그가 삭제되었습니다", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(CalendarActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}

//package com.example.bravenewworld;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CalendarView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.util.Calendar;
//import java.util.TimeZone;
//
//public class CalendarActivity extends AppCompatActivity {
//
//    private CalendarView calendarView;
//    private Button deleteButton;
//    private TextView logTextView;
//    private long selectedDateMillis;
//    private Button backButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);
//
//        calendarView = findViewById(R.id.calendarView);
//        deleteButton = findViewById(R.id.buttonDelete);
//        logTextView = findViewById(R.id.logTextView);
//        backButton = findViewById(R.id.buttonBack);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                Calendar selectedDate = Calendar.getInstance();
//                selectedDate.set(year, month, dayOfMonth);
//                selectedDate.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//                selectedDate.set(Calendar.HOUR_OF_DAY, 0);
//                selectedDate.set(Calendar.MINUTE, 0);
//                selectedDate.set(Calendar.SECOND, 0);
//                selectedDate.set(Calendar.MILLISECOND, 0);
//                selectedDateMillis = selectedDate.getTimeInMillis();
//
//                String log = GameLogStorage.getLog(CalendarActivity.this, selectedDateMillis);
//                if (log != null) {
//                    logTextView.setText(log);
//                } else {
//                    logTextView.setText("No log for this date");
//                }
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedDateMillis > 0) {
//                    GameLogStorage.deleteLog(CalendarActivity.this, selectedDateMillis);
//                    logTextView.setText("No log for this date");
//                    Toast.makeText(CalendarActivity.this, "로그가 삭제되었습니다", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(CalendarActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
package com.example.bravenewworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    // UI 요소 선언
    private CalendarView calendarView; // 달력을 표시하는 뷰
    private Button deleteButton; // 로그 삭제 버튼
    private TextView logTextView; // 로그를 표시하는 텍스트 뷰
    private long selectedDateMillis; // 선택된 날짜의 밀리초 값 저장
    private Button gameButton; // 게임 화면으로 이동하는 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar); // activity_calendar 레이아웃을 설정

        // UI 요소 초기화
        calendarView = findViewById(R.id.calendarView); // activity_calendar.xml에 정의된 달력 뷰를 연결
        deleteButton = findViewById(R.id.buttonDelete); // 삭제 버튼을 연결
        logTextView = findViewById(R.id.logTextView); // 로그 텍스트 뷰를 연결
        gameButton = findViewById(R.id.buttonGame); // 게임 버튼을 연결


        // 게임 버튼 클릭 이벤트 처리
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent를 사용하여 MainActivity로 이동하는 인텐트를 생성
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent); // 생성한 인텐트를 사용하여 새로운 액티비티(MainActivity)를 시작
            }
        });

    // 달력에서 날짜 변경 이벤트 처리
        // 이 코드는 달력에서 날짜를 선택할 때 호출되는 이벤트 리스너를 설정합니다. 사용자가 달력에서 날짜를 변경하면 내부의 onSelectedDayChange 메서드가 호출됩니다.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            //이 메서드는 사용자가 달력에서 특정 날짜를 선택할 때 호출됩니다.
            // year, month, dayOfMonth 매개변수는 사용자가 선택한 연도, 월, 일 정보를 제공합니다.
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 사용자가 달력에서 날짜를 선택할 때 호출되는 콜백 메서드

                // 선택된 날짜를 Calendar 객체로 변환
                Calendar selectedDate = Calendar.getInstance(); // 현재 날짜와 시간으로 설정된 Calendar 객체를 가져옴
                selectedDate.set(year, month, dayOfMonth); // 사용자가 선택한 연도, 월, 일로 설정
                selectedDate.setTimeZone(TimeZone.getTimeZone("Asia/Seoul")); // 타임존을 'Asia/Seoul'로 설정
                selectedDate.set(Calendar.HOUR_OF_DAY, 0); // 시간을 0으로 설정 (해당 날짜의 자정)
                selectedDate.set(Calendar.MINUTE, 0); // 분을 0으로 설정
                selectedDate.set(Calendar.SECOND, 0); // 초를 0으로 설정
                selectedDate.set(Calendar.MILLISECOND, 0); // 밀리초를 0으로 설정
                selectedDateMillis = selectedDate.getTimeInMillis(); // 선택된 날짜를 밀리초로 변환하여 저장

                // 선택된 날짜의 로그를 SharedPreferences에서 가져와서 표시
                String log = GameLogStorage.getLog(CalendarActivity.this, selectedDateMillis); // GameLogStorage에서 선택된 날짜의 로그를 가져옴
                if (log != null) {
                    logTextView.setText(log); // 로그가 있으면 표시
                } else {
                    logTextView.setText("No log for this date"); // 로그가 없으면 해당 메시지 표시
                }
            }
        });

        // 삭제 버튼 클릭 이벤트 처리
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDateMillis > 0) { // 선택된 날짜가 유효한지 확인
                    // 선택된 날짜의 로그를 삭제하고 UI를 업데이트
                    GameLogStorage.deleteLog(CalendarActivity.this, selectedDateMillis); // GameLogStorage에서 선택된 날짜의 로그를 삭제
                    logTextView.setText("No log for this date"); // 로그가 삭제되었음을 표시
                    Toast.makeText(CalendarActivity.this, "로그가 삭제되었습니다", Toast.LENGTH_SHORT).show(); // 토스트 메시지로 사용자에게 알림
                } else {
                    Toast.makeText(CalendarActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show(); // 유효한 날짜가 선택되지 않은 경우 알림
                }
            }
        });
    }
}

/**
 *
 * gameButton.setOnClickListener를 사용하여 게임 버튼 클릭 시 MainActivity로 이동하는 인텐트를 생성하고 시작합니다.
 * java
 * 코드 복사
 * gameButton.setOnClickListener(new View.OnClickListener() {
 *     @Override
 *     public void onClick(View v) {
 *         // Intent를 사용하여 MainActivity로 이동하는 인텐트를 생성
 *         Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
 *         startActivity(intent); // 생성한 인텐트를 사용하여 새로운 액티비티(MainActivity)를 시작
 *     }
 * });
 * 달력 날짜 변경 이벤트 처리:
 *
 * calendarView.setOnDateChangeListener를 사용하여 달력에서 날짜가 변경될 때의 동작을 정의합니다.
 * onSelectedDayChange 메서드에서 선택된 날짜를 Calendar 객체로 변환하고, 시간을 자정으로 설정하여 해당 날짜의 시작 시간을 selectedDateMillis 변수에 저장합니다.
 * GameLogStorage.getLog 메서드를 사용하여 선택된 날짜의 로그를 가져와 logTextView에 표시합니다.
 * java
 * 코드 복사
 * calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
 *     @Override
 *     public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
 *         // 사용자가 달력에서 날짜를 선택할 때 호출되는 콜백 메서드
 *
 *         // 선택된 날짜를 Calendar 객체로 변환
 *         Calendar selectedDate = Calendar.getInstance(); // 현재 날짜와 시간으로 설정된 Calendar 객체를 가져옴
 *         selectedDate.set(year, month, dayOfMonth); // 사용자가 선택한 연도, 월, 일로 설정
 *         selectedDate.setTimeZone(TimeZone.getTimeZone("Asia/Seoul")); // 타임존을 'Asia/Seoul'로 설정
 *         selectedDate.set(Calendar.HOUR_OF_DAY, 0); // 시간을 0으로 설정 (해당 날짜의 자정)
 *         selectedDate.set(Calendar.MINUTE, 0); // 분을 0으로 설정
 *         selectedDate.set(Calendar.SECOND, 0); // 초를 0으로 설정
 *         selectedDate.set(Calendar.MILLISECOND, 0); // 밀리초를 0으로 설정
 *         selectedDateMillis = selectedDate.getTimeInMillis(); // 선택된 날짜를 밀리초로 변환하여 저장
 *
 *         // 선택된 날짜의 로그를 SharedPreferences에서 가져와서 표시
 *         String log = GameLogStorage.getLog(CalendarActivity.this, selectedDateMillis); // GameLogStorage에서 선택된 날짜의 로그를 가져옴
 *         if (log != null) {
 *             logTextView.setText(log); // 로그가 있으면 표시
 *         } else {
 *             logTextView.setText("No log for this date"); // 로그가 없으면 해당 메시지 표시
 *         }
 *     }
 * });
 * 삭제 버튼 클릭 이벤트 처리:
 *
 * deleteButton.setOnClickListener를 사용하여 삭제 버튼 클릭 시의 동작을 정의합니다.
 * selectedDateMillis가 0보다 큰지 확인하여 유효한 날짜가 선택되었는지 확인합니다.
 * GameLogStorage.deleteLog 메서드를 사용하여 선택된 날짜의 로그를 삭제하고, 로그가 삭제되었음을 logTextView에 표시합니다.
 * Toast.makeText 메서드를 사용하여 로그가 삭제되었음을 사용자에게 알립니다.
 * 유효한 날짜가 선택되지 않은 경우에는 "날짜를 선택해주세요"라는 메시지를 사용자에게 표시합니다.
 * java
 * 코드 복사
 * deleteButton.setOnClickListener(new View.OnClickListener() {
 *     @Override
 *     public void onClick(View v) {
 *         if (selectedDateMillis > 0) { // 선택된 날짜가 유효한지 확인
 *             // 선택된 날짜의 로그를 삭제하고 UI를 업데이트
 *             GameLogStorage.deleteLog(CalendarActivity.this, selectedDateMillis); // GameLogStorage에서 선택된 날짜의 로그를 삭제
 *             logTextView.setText("No log for this date"); // 로그가 삭제되었음을 표시
 *             Toast.makeText(CalendarActivity.this, "로그가 삭제되었습니다", Toast.LENGTH_SHORT).show(); // 토스트 메시지로 사용자에게 알림
 *         } else {
 *             Toast.makeText(CalendarActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show(); // 유효한 날짜가 선택되지 않은 경우 알림
 *         }
 *     }
 * });
 */