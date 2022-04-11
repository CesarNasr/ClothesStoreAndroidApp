package com.example.clothesstoreapp.ui.composeui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

//class BasketScreen : Fragment(){
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return ComposeView(requireContext()).apply {
//            setContent {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Magenta),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "BASKETzzz",
//                        fontSize = MaterialTheme.typography.h3.fontSize,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun BasketScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "BASKET",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
@Preview
fun BasketScreenPreview() {
    BasketScreen()
}