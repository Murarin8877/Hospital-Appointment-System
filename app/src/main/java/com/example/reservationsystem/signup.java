package com.example.reservationsystem;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    //FirebaseDatabase rootNode ;
    //DatabaseReference reference ;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reservation-system-d7712-default-rtdb.firebaseio.com");

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            this.finish();
            return true;
        }
        return false;
    }



    //自己寫的函數 來判斷欄位是否符合
    public static boolean isContainAll(String str) {
        boolean isDigit = false;//定義一個boolean值，用來表示是否包含數字
        boolean isLowerCase = false;//定義一個boolean值，用來表示是否包含字母
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包裝類中的判斷數字的方法判斷每一個字元
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包裝類中的判斷字母的方法判斷每一個字元
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLowerCase && isUpperCase && str.matches(regex) && (str.length()<=16 && str.length()>=8);
        return isRight;
    }

    //是否是全數字
    public  static boolean isdigit(String str)
    {
        boolean isDigit=false;
        for (int i =0;i<str.length();i++)
        {
            if (Character.isDigit(str.charAt(i)))
            {
                isDigit = true;
            }
        }
        String regex = "[0-9]*";
        boolean isRight = isDigit && str.matches(regex);
        return isRight;

    }
    //判斷第一個字元是否是英文
    public boolean checkFirstEnglish(String fstrData)
    {
        char c = fstrData.charAt(0);
        if(((c>='a'&&c<='z' ) || (c>='A'&&c<='Z')))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //判斷身分證第一個字後面是不是數字
    public boolean isDigit(String word) {
        boolean check = true;
        for(int i=1;i<word.length();i++) {
            int c = (int)word.charAt(i);
            if(c>=48 && c<=57 ) {
                //是數字
            } else {
                check = false;
            }
        }
        return check;
    }
    boolean checkid = false;
    boolean checkname=false;
    boolean checkspassword = false;
    boolean checkpassword = false;









    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getSupportActionBar().hide();
        final EditText id = findViewById(R.id.idcard);//註冊畫面帳號
        EditText password_signup = findViewById(R.id.password_signup);//密碼
        EditText spassword_signup = findViewById(R.id.spd_signup);//確認密碼
        EditText name_signup = findViewById(R.id.Name_siginup);
        TextInputEditText tt = findViewById(R.id.idcard);
        TextView txv =findViewById(R.id.txv);
        Button signin_btn = findViewById(R.id.signin_btn);


        TextInputLayout checkidLayout = findViewById(R.id.textInputLayout_id);
        TextInputLayout checkpasswordLayout = findViewById(R.id.textInputLayout_pd);
        TextInputLayout checkspasswordLayout = findViewById(R.id.textInputLayout_spd);
        TextInputLayout checknameLayout = findViewById(R.id.textInputLayout_name);









        //監聽名字
        checknameLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (checknameLayout.getEditText().getText().toString().isEmpty() || checknameLayout.getEditText().getText().toString().length()<2 || checknameLayout.getEditText().getText().toString().length()>8 )
                {
                    checknameLayout.getEditText().setError("名字輸入錯誤");
                    checkname = false;
                    //這裡也可以新增boolen做判斷 btn底下就可以不用寫了
                }
                else
                {
                    checknameLayout.setError(null);
                    checkname = true;
                }
            }
        });



        //監聽確認密碼
        checkspasswordLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!checkpasswordLayout.getEditText().getText().toString().equals(checkspasswordLayout.getEditText().getText().toString()))
                {
                    checkspasswordLayout.getEditText().setError("密碼不一致");
                    checkspassword = false;
                }
                else
                {
                    checkspasswordLayout.setError(null);
                    checkspassword = true;
                }

            }
        });


        //密碼監聽
        checkpasswordLayout.getEditText().addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //String checkpasswordtext = checkpasswordLayout.getEditText().getText().toString().trim();
                if(!isContainAll(checkpasswordLayout.getEditText().getText().toString()))
                {
                    checkpasswordLayout.setError("密碼輸入錯誤");
                    checkpassword = false;

                }
                else
                {
                    checkpasswordLayout.setError(null);
                    checkpassword = true;
                }

            }
        });
        //設置edittext 對象的鍵位監聽事件 迴車鍵跟finish衝突
        checkidLayout.getEditText().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_DEL)
                {
                    //擷取目前id中的內容
                    String text = id.getText().toString();

                    if(0<text.length())
                    {
                        if (0==text.length()-1)
                        {
                            id.setText("");
                            id.setSelection(0);
                        }
                        else
                        {
                            String newText = text.substring(0,text.length()-1);

                            id.setText(newText);

                            id.setSelection(newText.length());
                        }
                    }
                    return true;
                }
                return false;
            }
        });









        //監聽idcard這個text是否超出限制
        checkidLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!checkFirstEnglish(checkidLayout.getEditText().getText().toString()))
                {
                    checkidLayout.setError("身分證輸入錯誤");
                    checkid = false;
                }
                else if(!isDigit(checkidLayout.getEditText().getText().toString()))
                {
                    checkidLayout.setError("身分證輸入錯誤數字");
                    checkid = false;
                }
                else if(checkidLayout.getEditText().getText().toString().trim().length()!=10)
                {
                    checkidLayout.setError("請輸入10位數字");
                    checkid = false;
                }

                else
                {
                    checkid = true;
                    checkidLayout.setError(null);
                }


            }
        });



        //監聽註冊按鈕
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference("users");
                String idcard = id.getText().toString();//註冊畫面帳號
                String repassword = password_signup.getText().toString();//密碼
                String resspassword = spassword_signup.getText().toString();//確認密碼
                String name = name_signup.getText().toString();
                if(idcard.isEmpty() && name.isEmpty() && repassword.isEmpty() && resspassword.isEmpty())
                {
                    Toast.makeText(signup.this, "請輸入身分證、姓名、密碼", Toast.LENGTH_SHORT).show();
                    checkspasswordLayout.requestFocus();
                    checkpasswordLayout.requestFocus();
                    checkidLayout.requestFocus();
                    return;
                }
                else if(idcard.isEmpty())
                {
                    Toast.makeText(signup.this, "請輸入身分證", Toast.LENGTH_SHORT).show();
                    checkidLayout.requestFocus();
                    return;
                }
                else if(repassword.isEmpty() || resspassword.isEmpty())
                {
                    Toast.makeText(signup.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                    checkpasswordLayout.requestFocus();
                    return;
                }
                else if  (!repassword.equals(resspassword))//!是 if not
                {
                    checkspasswordLayout.setError("密碼不一致");
                    checkspasswordLayout.requestFocus();
                    return;
                }
                else if(id.length()!=10)
                {
                    Toast.makeText(signup.this, "身分證只能輸入10位", Toast.LENGTH_SHORT).show();
                    checkidLayout.requestFocus();
                    return;
                }
                else if(!isContainAll(repassword) || repassword.length()<8 && repassword.length()>16)
                {
                    Toast.makeText(signup.this, "8-16位密碼必須同時包含大小寫字母及數字", Toast.LENGTH_SHORT).show();
                    checkpasswordLayout.requestFocus();
                    return;
                }
                else if(!checkname || !checkid || !checkpassword || !checkspassword)//確認全部資料是否正確
                {
                    Toast.makeText(signup.this, "請檢查資料是否錯誤", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(id.getText().toString()))
                            {
                                Toast.makeText(signup.this,"此帳號已被使用",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(signup.this, "註冊成功，趕快登入吧", Toast.LENGTH_SHORT).show();
                                UserHelperClass userHelperClass = new UserHelperClass(idcard,name,repassword,resspassword);
                                databaseReference.child("users").child(idcard).setValue(userHelperClass);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });








    }


}