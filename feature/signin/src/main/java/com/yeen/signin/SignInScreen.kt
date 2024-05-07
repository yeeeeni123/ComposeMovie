package com.yeen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeen.signin.R
import com.yeen.login.ComposeUI
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(
    onLoginClick: (Int) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val emailState = viewModel.userEmail.value
    val passwordState = viewModel.userPassword.value

    val snackBarHostState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackBarHostState)
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SignInViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                    )
                }

                is SignInViewModel.UiEvent.SignIn -> {
                    snackBarHostState.showSnackbar(
                        message = "회원가입을 성공했습니다."
                    )
                    onLoginClick(0)
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Column(
            Modifier.fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 10.dp)
                .clickable {
                    onLoginClick(0)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                Modifier.size(25.dp)
            )
        }
            Spacer(modifier = Modifier.height(20.dp))

            var userId = ComposeUI().editText("이메일 아이디", "아이디를 입력해주세요", KeyboardType.Email)
            var userPw = ComposeUI().pwEditText("비밀번호", "비밀번호를 입력해주세요", KeyboardType.Password)
            var userPwCheck = ComposeUI().pwEditText("비밀번호 확인", "비밀번호를 입력해주세요", KeyboardType.Password)

            viewModel.onEvent(SignInEvent.EnteredEmail(userId.text))
            viewModel.onEvent(SignInEvent.EnteredPassword(userPw.text))

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                Modifier.fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = colorResource(id = R.color.brightRed),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        viewModel.onEvent(SignInEvent.SignIn)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text("회원가입", color = Color.White, fontSize = 16.sp)
            }

        }
    }
