//package com.example.bravenewworld;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.util.Calendar;
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText gameNameEditText, goalEditText, progressEditText, searchEditText;
//    private TextView timerTextView, logTextView;
//    private Button startButton, saveButton, successButton, failButton, searchButton, deleteButton, calendarButton, resetButton; // 초기화 버튼 추가
//    private long startTime, timeInMillis;
//    private Handler timerHandler = new Handler();
//    private Runnable timerRunnable = new Runnable() {
//        @Override
//        public void run() {
//            long millis = System.currentTimeMillis() - startTime;
//            int seconds = (int) (millis / 1000);
//            int minutes = seconds / 60;
//            seconds = seconds % 60;
//            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
//            timerHandler.postDelayed(this, 500);
//        }
//    };
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        gameNameEditText = findViewById(R.id.editTextGameName);
//        goalEditText = findViewById(R.id.editTextGoal);
//        progressEditText = findViewById(R.id.editTextProgress);
//        searchEditText = findViewById(R.id.editTextSearch);
//        timerTextView = findViewById(R.id.textViewTimer);
//        logTextView = findViewById(R.id.textViewLog);
//
//        startButton = findViewById(R.id.buttonStart);
//        saveButton = findViewById(R.id.buttonSave);
//        successButton = findViewById(R.id.buttonSuccess);
//        failButton = findViewById(R.id.buttonFail);
//        searchButton = findViewById(R.id.buttonSearch);
//        deleteButton = findViewById(R.id.buttonDelete);
//        calendarButton = findViewById(R.id.buttonCalendar);
//        resetButton = findViewById(R.id.buttonReset); // 초기화 버튼 연결
//
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentText = startButton.getText().toString();
//                switch (currentText) {
//                    case "시작":
//                        startTime = System.currentTimeMillis();
//                        timerHandler.postDelayed(timerRunnable, 0);
//                        startButton.setText("정지");
//                        break;
//                    case "정지":
//                        timerHandler.removeCallbacks(timerRunnable);
//                        startButton.setText("재개");
//                        timeInMillis = System.currentTimeMillis() - startTime; // 정지할 때 시간 기록
//                        break;
//                    case "재개":
//                        startTime = System.currentTimeMillis() - timeInMillis;
//                        timerHandler.postDelayed(timerRunnable, 0);
//                        startButton.setText("정지");
//                        break;
//                }
//            }
//        });
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String gameName = gameNameEditText.getText().toString();
//                String goal = goalEditText.getText().toString();
//                String progress = progressEditText.getText().toString();
//                String timer = timerTextView.getText().toString(); // 타이머 시간 추가
//                String log = "날짜: " + Calendar.getInstance().getTime().toString() +
//                        "\n게임종류: " + gameName +
//                        "\n목표: " + goal +
//                        "\n진행도: " + progress +
//                        "\n타이머: " + timer; // 타이머 시간 추가
//
//                logTextView.setText(log);
//
//                // 데이터 저장
//                long date = new java.util.GregorianCalendar(
//                        Calendar.getInstance().get(Calendar.YEAR),
//                        Calendar.getInstance().get(Calendar.MONTH),
//                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//                ).getTimeInMillis();
//
//                GameLogStorage.saveLog(MainActivity.this, date, log);
//
//                Toast.makeText(MainActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        successButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logTextView.setText("Goal Achieved");
//                saveLogWithStatus("Goal Achieved"); // 성공 상태 저장
//            }
//        });
//
//        failButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String progress = progressEditText.getText().toString();
//                logTextView.setText("Failed, Progress: " + progress);
//                saveLogWithStatus("Failed, Progress: " + progress); // 실패 상태 저장
//            }
//        });
//
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String gameName = searchEditText.getText().toString();
//                searchOnYouTube(gameName);
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameNameEditText.setText("");
//                goalEditText.setText("");
//                progressEditText.setText("");
//                logTextView.setText("");
//                timerTextView.setText("00:00");
//
//                // 로그 삭제 추가
//                long date = new java.util.GregorianCalendar(
//                        Calendar.getInstance().get(Calendar.YEAR),
//                        Calendar.getInstance().get(Calendar.MONTH),
//                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//                ).getTimeInMillis();
//
//                GameLogStorage.deleteLog(MainActivity.this, date);
//
//                Toast.makeText(MainActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        calendarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 타이머 초기화
//                timerHandler.removeCallbacks(timerRunnable);
//                startButton.setText("시작");
//                timerTextView.setText("00:00");
//                timeInMillis = 0;
//
//                Toast.makeText(MainActivity.this, "초기화되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void searchOnYouTube(String query) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + query));
//        startActivity(intent);
//    }
//
//    private void saveLogWithStatus(String status) {
//        String gameName = gameNameEditText.getText().toString();
//        String goal = goalEditText.getText().toString();
//        String progress = progressEditText.getText().toString();
//        String timer = timerTextView.getText().toString();
//        String log = "날짜: " + Calendar.getInstance().getTime().toString() +
//                "\n게임종류: " + gameName +
//                "\n목표: " + goal +
//                "\n진행도: " + progress +
//                "\n타이머: " + timer +
//                "\n상태: " + status; // 상태 추가
//
//        logTextView.setText(log);
//
//        // 데이터 저장
//        long date = new java.util.GregorianCalendar(
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        ).getTimeInMillis();
//
//        GameLogStorage.saveLog(MainActivity.this, date, log);
//
//        Toast.makeText(MainActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
//    }
//}
//
//package com.example.bravenewworld;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Locale;
//import java.util.TimeZone;
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText gameNameEditText, goalEditText, progressEditText, searchEditText;
//    private TextView timerTextView, logTextView;
//    private Button startButton, saveButton, successButton, failButton, searchButton, deleteButton, calendarButton, resetButton; // 초기화 버튼 추가
//    private long startTime, timeInMillis;
//    private Handler timerHandler = new Handler();
//    private Runnable timerRunnable = new Runnable() {
//        @Override
//        public void run() {
//            long millis = System.currentTimeMillis() - startTime;
//            int seconds = (int) (millis / 1000);
//            int minutes = seconds / 60;
//            seconds = seconds % 60;
//            timerTextView.setText(String.format(Locale.getDefault(), "%d:%02d", minutes, seconds));
//            timerHandler.postDelayed(this, 500);
//        }
//    };
//    private String currentStatus = ""; // 현재 상태를 저장할 변수
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        gameNameEditText = findViewById(R.id.editTextGameName);
//        goalEditText = findViewById(R.id.editTextGoal);
//        progressEditText = findViewById(R.id.editTextProgress);
//        searchEditText = findViewById(R.id.editTextSearch);
//        timerTextView = findViewById(R.id.textViewTimer);
//        logTextView = findViewById(R.id.textViewLog);
//
//        startButton = findViewById(R.id.buttonStart);
//        saveButton = findViewById(R.id.buttonSave);
//        successButton = findViewById(R.id.buttonSuccess);
//        failButton = findViewById(R.id.buttonFail);
//        searchButton = findViewById(R.id.buttonSearch);
//        deleteButton = findViewById(R.id.buttonDelete);
//        calendarButton = findViewById(R.id.buttonCalendar);
//        resetButton = findViewById(R.id.buttonReset); // 초기화 버튼 연결
//
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentText = startButton.getText().toString();
//                switch (currentText) {
//                    case "시작":
//                        startTime = System.currentTimeMillis();
//                        timerHandler.postDelayed(timerRunnable, 0);
//                        startButton.setText("정지");
//                        break;
//                    case "정지":
//                        timerHandler.removeCallbacks(timerRunnable);
//                        startButton.setText("재개");
//                        timeInMillis = System.currentTimeMillis() - startTime; // 정지할 때 시간 기록
//                        break;
//                    case "재개":
//                        startTime = System.currentTimeMillis() - timeInMillis;
//                        timerHandler.postDelayed(timerRunnable, 0);
//                        startButton.setText("정지");
//                        break;
//                }
//            }
//        });
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveLog("저장되었습니다");
//            }
//        });
//
//        successButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentStatus = "Goal Achieved";
//                logTextView.setText(currentStatus);
//            }
//        });
//
//        failButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String progress = progressEditText.getText().toString();
//                currentStatus = "Failed, Progress: " + progress;
//                logTextView.setText(currentStatus);
//            }
//        });
//
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String gameName = searchEditText.getText().toString();
//                searchOnYouTube(gameName);
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameNameEditText.setText("");
//                goalEditText.setText("");
//                progressEditText.setText("");
//                logTextView.setText("");
//                timerTextView.setText("00:00");
//
//                long date = getCurrentDateInMillis();
//
//                GameLogStorage.deleteLog(MainActivity.this, date);
//
//                Toast.makeText(MainActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        calendarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 타이머 초기화
//                timerHandler.removeCallbacks(timerRunnable);
//                startButton.setText("시작");
//                timerTextView.setText("00:00");
//                timeInMillis = 0;
//
//                Toast.makeText(MainActivity.this, "초기화되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void searchOnYouTube(String query) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + query));
//        startActivity(intent);
//    }
//
//    private void saveLog(String message) {
//        String gameName = gameNameEditText.getText().toString();
//        String goal = goalEditText.getText().toString();
//        String progress = progressEditText.getText().toString();
//        String timer = timerTextView.getText().toString();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
//
//        String log = "날짜: " + formattedDate +
//                "\n게임종류: " + gameName +
//                "\n목표: " + goal +
//                "\n진행도: " + progress +
//                "\n타이머: " + timer +
//                "\n상태: " + currentStatus;
//
//        logTextView.setText(log);
//
//        long date = getCurrentDateInMillis();
//
//        GameLogStorage.saveLog(MainActivity.this, date, log);
//
//        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private long getCurrentDateInMillis() {
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTimeInMillis();
//    }
//}
package com.example.bravenewworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    // UI 요소 선언
    private EditText gameNameEditText, goalEditText, progressEditText, searchEditText;
    private TextView timerTextView, logTextView;
    private Button startButton, saveButton, successButton, failButton, searchButton, deleteButton, calendarButton, resetButton;
    private long startTime, timeInMillis; // 타이머 시작 시간과 경과 시간을 저장할 변수
    private Handler timerHandler = new Handler(); // 타이머 업데이트를 위한 핸들러
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            // 타이머의 경과 시간을 밀리초로 계산
            long millis = System.currentTimeMillis() - startTime;
            // 밀리초를 초 단위로 변환
            int seconds = (int) (millis / 1000);
            // 초를 분 단위로 변환
            int minutes = seconds / 60;
            // 분을 제외한 나머지 초
            seconds = seconds % 60;
            // 경과 시간을 TextView에 표시
            timerTextView.setText(String.format(Locale.getDefault(), "%d:%02d", minutes, seconds));
            // 500ms마다 이 Runnable을 다시 실행
            timerHandler.postDelayed(this, 500);
        }
    };
    private String currentStatus = ""; // 현재 상태를 저장할 변수

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소 초기화
        gameNameEditText = findViewById(R.id.editTextGameName);
        goalEditText = findViewById(R.id.editTextGoal);
        progressEditText = findViewById(R.id.editTextProgress);
        searchEditText = findViewById(R.id.editTextSearch);
        timerTextView = findViewById(R.id.textViewTimer);
        logTextView = findViewById(R.id.textViewLog);

        startButton = findViewById(R.id.buttonStart);
        saveButton = findViewById(R.id.buttonSave);
        successButton = findViewById(R.id.buttonSuccess);
        failButton = findViewById(R.id.buttonFail);
        searchButton = findViewById(R.id.buttonSearch);
        deleteButton = findViewById(R.id.buttonDelete);
        calendarButton = findViewById(R.id.buttonCalendar);
        resetButton = findViewById(R.id.buttonReset); // 초기화 버튼 연결

// 타이머 시작/정지/재개 버튼 클릭 이벤트 처리
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 버튼의 텍스트를 가져와서 currentText 변수에 저장
                String currentText = startButton.getText().toString();
                // currentText 값에 따라 다른 동작을 수행
                switch (currentText) {
                    case "시작":
                        // 타이머 시작
                        startTime = System.currentTimeMillis(); // 현재 시간을 밀리초로 기록
                        timerHandler.postDelayed(timerRunnable, 0); // 타이머 Runnable을 실행
                        startButton.setText("정지"); // 버튼 텍스트를 "정지"로 변경
                        break;
                    case "정지":
                        // 타이머 정지
                        timerHandler.removeCallbacks(timerRunnable); // 타이머 Runnable 중지
                        startButton.setText("재개"); // 버튼 텍스트를 "재개"로 변경
                        timeInMillis = System.currentTimeMillis() - startTime; // 경과 시간을 기록
                        break;
                    case "재개":
                        // 타이머 재개
                        startTime = System.currentTimeMillis() - timeInMillis; // 시작 시간을 경과 시간만큼 조정
                        timerHandler.postDelayed(timerRunnable, 0); // 타이머 Runnable을 다시 실행
                        startButton.setText("정지"); // 버튼 텍스트를 "정지"로 변경
                        break;
                }
            }
        });

        // 저장 버튼 클릭 이벤트 처리
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLog("저장되었습니다"); // 로그를 저장하고 메시지를 표시
            }
        });

        // 성공 버튼 클릭 이벤트 처리
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStatus = "목표 달성"; // 현재 상태를 "목표 달성"으로 설정
                logTextView.setText(currentStatus); // 상태를 로그에 표시
                // 버튼 색상 변경
                successButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_light));
                failButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray));
            }
        });

        // 실패 버튼 클릭 이벤트 처리
        failButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String progress = progressEditText.getText().toString();
                currentStatus = "실패 : " + progress; // 현재 상태를 "실패"와 진행도로 설정
                logTextView.setText(currentStatus); // 상태를 로그에 표시
                // 버튼 색상 변경
                failButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));
                successButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray));
            }
        });

        // 검색 버튼 클릭 이벤트 처리
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameName = searchEditText.getText().toString();
                searchOnYouTube(gameName); // 유튜브 검색을 실행
            }
        });

// 삭제 버튼 클릭 이벤트 처리
        // deleteButton.setOnClickListener를 사용하여 삭제 버튼이 클릭될 때 실행될 코드를 정의합니다.
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력 필드와 상태 초기화
                gameNameEditText.setText(""); // 게임 이름 입력 필드를 빈 문자열로 설정하여 초기화
                goalEditText.setText(""); // 목표 입력 필드를 빈 문자열로 설정하여 초기화
                progressEditText.setText(""); // 진행도 입력 필드를 빈 문자열로 설정하여 초기화
                logTextView.setText(""); // 로그 텍스트뷰를 빈 문자열로 설정하여 초기화
                timerTextView.setText("00:00"); // 타이머 텍스트뷰를 "00:00"으로 설정하여 초기화
                currentStatus = ""; // 현재 상태 변수를 빈 문자열로 초기화
                successButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray)); // 성공 버튼 색을 기본 색으로 변경
                failButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray)); // 실패 버튼 색을 기본 색으로 변경

                long date = getCurrentDateInMillis(); // 현재 날짜를 밀리초로 변환

                GameLogStorage.deleteLog(MainActivity.this, date); // GameLogStorage 클래스의 deleteLog 메서드를 호출하여
                // 현재 날짜에 해당하는 로그를 삭제합니다 date는 현재 날짜를 밀리초로 변환한 값입니다.

                Toast.makeText(MainActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show(); // 삭제 완료 메시지 표시
            }
        });

        // 달력 버튼 클릭 이벤트 처리
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CalendarActivity로 이동
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

// 초기화 버튼 클릭 이벤트 처리
        ////resetButton.setOnClickListener를 사용하여 초기화 버튼이 클릭될 때 실행될 코드를 정의합니다.
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 타이머 초기화
                timerHandler.removeCallbacks(timerRunnable);  // 타이머를 중지시킵니다.
                /**
                 * timerHandler는 Handler 객체로, Runnable 객체(timerRunnable)를 일정 간격으로 실행하도록 합니다.
                 *  removeCallbacks 메서드를 호출하여 현재 실행 중인 timerRunnable을 중지합니다.
                 * 이로 인해 타이머가 더 이상 실행되지 않으며, 화면에 시간이 갱신되지 않습니다.
                 */
                startButton.setText("시작");  // 시작 버튼의 텍스트를 "시작"으로 변경합니다.
                timerTextView.setText("00:00");  // 타이머 표시를 "00:00"으로 초기화합니다.
                timeInMillis = 0;  // 타이머 경과 시간을 0으로 초기화합니다.

                Toast.makeText(MainActivity.this, "초기화되었습니다", Toast.LENGTH_SHORT).show();  // 초기화 완료 메시지를 표시합니다.
            }
        });
        }

    // 유튜브 검색 메서드
    private void searchOnYouTube(String query) {
        // 유튜브 검색을 실행하기 위한 인텐트 생성 및 시작
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + query));
        startActivity(intent);
    }

    // 로그 저장 메서드
    private void saveLog(String message) {
        // 입력된 데이터를 가져와서 문자열로 생성
        String gameName = gameNameEditText.getText().toString();
        String goal = goalEditText.getText().toString();
        String progress = progressEditText.getText().toString();
        String timer = timerTextView.getText().toString();

        // 날짜 형식을 설정하고 현재 시간을 포맷팅
        //SimpleDateFormat을 사용하여 날짜 형식을 "yyyy-MM-dd HH:mm:ss"로 설정합니다.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        // TimeZone.getTimeZone("Asia/Seoul")을 사용하여 시간대를 한국 시간으로 설정합니다.
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        // 현재 시간을 Calendar.getInstance().getTime()로 가져와 포맷팅하여 formattedDate 문자열로 저장합니다.
        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());

        // 로그 문자열 생성
        String log = "날짜: " + formattedDate +
                "\n게임종류: " + gameName +
                "\n목표: " + goal +
                "\n진행도: " + progress +
                "\n타이머: " + timer +
                "\n상태: " + currentStatus;
        /**
         * 포맷팅된 날짜와 함께 게임 종류, 목표, 진행도, 타이머, 현재 상태를 포함한 로그 문자열을 생성합니다.
         * log 변수에 완성된 로그 문자열을 저장합니다.
         */

        logTextView.setText(log); // 로그를 화면에 표시

        long date = getCurrentDateInMillis(); // 현재 날짜를 밀리초로 변환

        GameLogStorage.saveLog(MainActivity.this, date, log); // 로그를 저장

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show(); // 메시지 표시
    }

    // 현재 날짜를 밀리초 단위로 반환하는 메서드
    private long getCurrentDateInMillis() {
        // 현재 시간을 가져옴 : Calendar 객체를 생성하여 현재 시간을 가져옵니다. TimeZone.getTimeZone("Asia/Seoul")을 사용하여 한국 시간대로 설정합니다.
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        // 시간을 0으로 설정하여 하루의 시작 시간을 기준으로 함 : HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND를 0으로 설정하여 해당 날짜의 시작 시간을 기준으로 설정합니다.
        // 이렇게 하면 특정 날짜의 로그를 저장하거나 검색할 때 시간과 무관하게 일치하는 날짜 값을 사용할 수 있습니다.
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 밀리초 단위로 반환 : 설정된 날짜와 시간을 밀리초 단위로 반환합니다. 이 값은 날짜 단위로 로그를 저장하거나 검색할 때 사용됩니다.
        return calendar.getTimeInMillis();
    }
}

/**
 * 밀리초 단위로 변환한 이유
 * 날짜 단위로 저장 및 비교:
 *
 * 시간을 0으로 설정하여 하루의 시작 시간을 기준으로 하면, 특정 날짜의 로그를 저장하거나 검색할 때 시간을 무시하고 날짜 단위로만 비교할 수 있습니다. 이렇게 하면 같은 날짜에 여러 번 저장하더라도 같은 키 값으로 관리할 수 있습니다.
 * 예를 들어, 2024년 5월 24일의 시작 시간을 기준으로 밀리초 단위로 저장하면, 해당 날짜의 로그를 검색하거나 삭제할 때 정확하게 일치하는 날짜 값을 사용할 수 있습니다.
 * 시간대 설정:
 *
 * Calendar 객체를 생성할 때 한국 시간대(Asia/Seoul)를 설정하여 시간대에 맞는 올바른 시간을 가져옵니다. 이는 한국 시간대를 기준으로 로그를 관리하기 위해 필요합니다.
 * 일관된 데이터 관리:
 *
 * 시간과 분, 초, 밀리초를 0으로 설정하면, 날짜 비교 시 시간에 의한 변동을 피할 수 있어 일관된 데이터 관리를 할 수 있습니다.
 */
