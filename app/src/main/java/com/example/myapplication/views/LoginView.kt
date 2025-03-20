package com.example.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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


@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Witaj w Compose!",
        modifier = Modifier
            .offset(x = 149.dp, y = 62.dp)
            .size(129.dp)
            .width(129.dp)
            .height(129.dp)
    )
}


@Composable
fun LoginPage(navController: NavController) {
    var email by remember { mutableStateOf("") }

    OutlinedTextField(

        shape = RoundedCornerShape(15.dp),
        value = email,
        onValueChange = { email = it },
        label = {
            Text(
                text = "Email or User Name",
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                ),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(50.dp)
            .offset(y = 294.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.user), // Use your image resource here (e.g., ImageVector)
                contentDescription = "Email Icon",
                modifier = Modifier
                .size(30.dp)
            )
        }

    )

    OutlinedTextField(
        shape = RoundedCornerShape(15.dp),
        value = email,
        onValueChange = { email = it },
        label = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(50.dp)
            .offset(y = 384.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = "Email Icon",
                modifier = Modifier
                    .size(30.dp)
            )
        }


    )

    LogoImage()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Sign in",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                color = Color(0xFF471AA0),
                fontSize = 30.sp,
            ),
            modifier = Modifier
                .offset(x = 19.dp, y = 212.dp)
        )


    }


    Button(
        onClick = { navController.navigate(Routes.homePage) },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFBB84E8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(50.dp)
            .offset(y = 532.dp)
    ) {
        Text(
            text = "Sign In",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                fontSize = 15.sp,
            )
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Forget Password?",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                color = Color(0xFF471AA0),
                fontSize = 15.sp,
            ),
            modifier = Modifier
                .offset(x = 276.dp, y = 474.dp)
        )
    }
    Text(
        text = "Don't have account ?",
        style = TextStyle(
            fontWeight = FontWeight.W400,
            color = Color(0xFF471AA0),
            fontSize = 15.sp,
        ),
        modifier = Modifier
            .offset(x = 110.dp, y = 852.dp)
    )
    Text(
        text = "Sing Up",
        style = TextStyle(
            fontWeight = FontWeight.W700,
            color = Color(0xFF471AA0),
            fontSize = 15.sp,
        ),
        modifier = Modifier
            .offset(x = 250.dp, y = 852.dp)
            .clickable {
                // Navigate to register page or any other action
                navController.navigate(Routes.registerPage)
            }
    )
}