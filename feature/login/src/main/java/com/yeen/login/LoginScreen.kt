package com.yeen.login

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yeen.login.R
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.coroutineContext

@Composable
fun LoginScreen(
    onSignInClick: (Int) -> Unit,
    onMainClick: (Int) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val (checked, setChecked) = remember { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackBarHostState)
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is LoginViewModel.UiEvent.Login -> {
                    snackBarHostState.showSnackbar(
                        message = "로그인을 성공했습니다."
                    )
                    onMainClick(0)
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
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.movie_login),
                contentDescription = null,
                Modifier.size(100.dp)
            )
        }

        var userId = ComposeUI().editText("이메일 아이디", "아이디를 입력해주세요", KeyboardType.Email)
        var userPw = ComposeUI().pwEditText("비밀번호", "비밀번호를 입력해주세요", KeyboardType.Password)

        viewModel.onEvent(LoginEvent.EnteredEmail(userId.text), checked)
        viewModel.onEvent(LoginEvent.EnteredPassword(userPw.text), checked)

        ComposeUI().CheckBoxRow(
            text = "자동 로그인",
            value = checked,
            onClick = { setChecked(!checked) })

        Column(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .height(50.dp)
                .background(
                    color = colorResource(id = R.color.brightRed),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    viewModel.onEvent(LoginEvent.Login, checked)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("로그인", color = Color.White, fontSize = 16.sp)
        }

        Text(text = "회원가입",
            style = androidx.compose.ui.text.TextStyle(
                textAlign = TextAlign.Right
            ),
            modifier = Modifier
                .padding(0.dp, 10.dp, 10.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    onSignInClick(1)
                })
    }
}