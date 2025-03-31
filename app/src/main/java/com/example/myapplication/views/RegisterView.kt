package com.example.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.utils.Routes
import com.example.myapplication.models.AuthTextField

@Composable
fun BackImage() {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = "Witaj w Compose!",
        modifier = Modifier
            .offset(x = 12.dp, y = 58.dp)
            .size(20.dp)
            .width(20.dp)
            .height(20.dp)
    )
}

@Composable
fun ElipceImage() {
    Image(
        painter = painterResource(id = R.drawable.ellipse),
        contentDescription = "Witaj w Compose!",
        modifier = Modifier
            .offset(x = 284.dp, y = 4.dp)
            .size(129.dp)
            .width(129.dp)
            .height(129.dp)
    )
}



@Composable
fun RegisterPage(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}

    BackImage()
    ElipceImage()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Sign Up",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                color = Color(0xFF471AA0),
                fontSize = 30.sp,
            ),
            modifier = Modifier
                .offset(x = 20.dp, y = 180.dp)
        )

        Spacer(modifier = Modifier.height(222.dp))

        AuthTextField(
            placeholder = "Full Name",
            value = email,
            onValueChange = { email = it },
            isPassword = false,
            iconId = R.drawable.user,


            )


        Spacer(modifier = Modifier.height(40.dp))

        AuthTextField(
            placeholder = "Email",
            value = email,
            onValueChange = { email = it },
            isPassword = false,
            iconId = R.drawable.email,


            )


        Spacer(modifier = Modifier.height(40.dp))
        AuthTextField(
            placeholder = "Password",
            value = password,
            onValueChange = { password = it },
            isPassword = true,
            iconId = R.drawable.password,

            )
        Spacer(modifier = Modifier.height(40.dp))
        AuthTextField(
            placeholder = "Confirm Password",
            value = password,
            onValueChange = { password = it },
            isPassword = true,
            iconId = R.drawable.password,

            )
        Spacer(modifier = Modifier.height(40.dp))
    }
    Button(
        onClick = {  },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFBB84E8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(50.dp)
            .offset(y = 662.dp)
    ) {
        Text(
            text = "Sign Up",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                fontSize = 15.sp,
            )
        )
    }
    Text(
        text = "Already have an account ?",
        style = TextStyle(
            fontWeight = FontWeight.W400,
            color = Color(0xFF471AA0),
            fontSize = 15.sp,
        ),
        modifier = Modifier
            .offset(x = 96.dp, y = 852.dp)
    )
    Text(
        text = "Sing In",
        style = TextStyle(
            fontWeight = FontWeight.W700,
            color = Color(0xFF471AA0),
            fontSize = 15.sp,
        ),
        modifier = Modifier
            .offset(x = 273.dp, y = 852.dp)
            .clickable {
                // Navigate to register page or any other action
                navController.navigate(Routes.loginPage)
            }
    )
    Text(
        text = "Back",
        style = TextStyle(
            fontWeight = FontWeight.W400,
            color = Color(0xFF471AA0),
            fontSize = 12.sp,
        ),
        modifier = Modifier
            .offset(x = 32.dp, y = 61.dp)
            .clickable {
                // Navigate to register page or any other action
                navController.navigate(Routes.loginPage)
            }
    )
}