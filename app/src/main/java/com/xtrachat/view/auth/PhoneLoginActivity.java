package com.xtrachat.view.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xtrachat.R;
import com.xtrachat.databinding.ActivityMainBinding;
import com.xtrachat.databinding.ActivityPhoneLoginBinding;
import com.xtrachat.databinding.ActivityPhoneLoginBindingImpl;
import com.xtrachat.model.users.Users;
import com.xtrachat.view.MainActivity;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {

    private static String TAG ="PhoneLoginActivity" ;
    private ActivityPhoneLoginBinding binding;

    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ProgressDialog progressDialog;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_login);


        //
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            startActivity(new Intent(this, SetUserInfoActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.btnNext.getText().toString().equals("Next")) {
                    progressDialog.setMessage("Please_Wait...");
                    progressDialog.show();

                    String phone = "+" + binding.codeCountry.getText().toString()+binding.edPhone.getText().toString();
                    startPhoneNumberVerification(phone);
                }
                else {
                    progressDialog.setMessage("Verifying...");
                    progressDialog.show();
                    verifyPhoneNumberWithCode(mVerificationId, binding.edCode.getText().toString());
                }
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                Log.d(TAG, "onVerificationCompleted: Complete");
                signInWithPhoneAuthCredential(phoneAuthCredential);
                progressDialog.dismiss();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d(TAG, "onVerificationFailed:"+e.getMessage());

            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                binding.btnNext.setText("Confirm");
                progressDialog.dismiss();

                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        };
        // [END phone_auth_callbacks]
    }

    private void startPhoneNumberVerification(String phoneNumber){
        // [START start_phone_auth]

        progressDialog.setMessage("Send code to : "+phoneNumber);
        progressDialog.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
        //[END start_phone_auth]
        //mVerificationInProgress = true;

    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        //[START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        //[END verify_with_code]

        signInWithPhoneAuthCredential(credential);
    }

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(PhoneLoginActivity.this, SetUserInfoActivity.class));

//                            if (user != null) {
//                                String userID = user.getUid();
//                                Users users = new Users(
//                                        userID,
//                                        "",
//                                        user.getPhoneNumber(),
//                                        "",
//                                        "",
//                                        "",
//                                        "",
//                                        "",
//                                        "",
//                                        "");
//                                firestore.collection("Users").document("UserInfo").collection(userID)
//                                        .add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                    @Override
//                                    public void onSuccess(DocumentReference documentReference) {
//                                    }
//                                });
//                            }
//                            else{
//                                Toast.makeText(getApplicationContext(), "Something went wrong!",Toast.LENGTH_SHORT).show();
//                            }
//                            startActivity(new Intent(PhoneLoginActivity.this, SetUserInfoActivity.class));
                        } else {
                            // Sign in failed, display a message and update the UI
                            progressDialog.dismiss();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid]
                                Log.d(TAG, "onComplete: Error Code");
                            }
                        }
                    }
                });
    }
    // [END sign_in_with_phone]

}