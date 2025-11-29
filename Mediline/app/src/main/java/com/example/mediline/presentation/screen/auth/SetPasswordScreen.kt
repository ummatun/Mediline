//@Composable
//fun SetPasswordScreen(
//    onComplete: () -> Unit
//) {
//    var password by remember { mutableStateOf("") }
//    var confirm by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .padding(horizontal = 32.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Spacer(Modifier.height(60.dp))
//
//        Text("Set Password", fontSize = 28.sp, fontWeight = FontWeight.Bold)
//
//        Spacer(Modifier.height(16.dp))
//
//        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
//            color = Color.Gray, fontSize = 14.sp)
//
//        Spacer(Modifier.height(48.dp))
//
//        AuthTextField(value = password, onValueChange = { password = it }, placeholder = "••••••••", isPassword = true)
//        Spacer(Modifier.height(16.dp))
//        AuthTextField(value = confirm, onValueChange = { confirm = it }, placeholder = "••••••••", isPassword = true)
//
//        Spacer(Modifier.height(48.dp))
//
//        Button(
//            onClick = onComplete,
//            modifier = Modifier.fillMaxWidth().height(56.dp),
//            shape = RoundedCornerShape(30.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF))
//        ) {
//            Text("Create New Password", color = Color.White, fontSize = 18.sp)
//        }
//    }
//}