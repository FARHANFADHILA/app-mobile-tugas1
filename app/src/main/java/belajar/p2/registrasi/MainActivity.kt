package belajar.p2.registrasi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import belajar.p2.registrasi.ui.theme.RegistrasiTheme
import java.time.LocalDate
import java.util.Calendar


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrasiTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Main_app()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_app(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "registrasi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = 160.dp)
                    .padding(30.dp)
            ) {
                bar_nama()
                bar_tggl()
            }

    }
}

var nama_user by mutableStateOf(TextFieldValue(""))
@Composable
fun bar_nama() {
    Column(

    ){
        Row(modifier = Modifier
            .fillMaxWidth()
        ){
            Text(text = "NAME ",modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(108.dp)

            )
            OutlinedTextField(value = nama_user, onValueChange ={
                nama_user = it
            }, label = { Text(text = "your name")}, maxLines = 1,

            )
        }
    }
}
var date by mutableStateOf("")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bar_tggl() {
    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)
    val month = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)
    val context = LocalContext.current

    val dateDialog = DatePickerDialog(
        context,{ d, year, month, day ->
            val month = month + 1
            date = "$day-$month-$year"
        }, year, month, day
    )
    val startIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
    }
    Column(
    ){
        Row {
            Text(text = "TANGGAL LAHIR ", modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(108.dp)
            )
            OutlinedButton(onClick = { dateDialog.show()},
                modifier = Modifier
                    .fillMaxWidth()
                ) {
                Text(text = "Date $date")
            }
        }
        Button(onClick = {
            val intent_tujuan = Intent(context,MainActivity2::class.java)
            intent_tujuan.putExtra("EXTRA_NAMA", nama_user.text)
            intent_tujuan.putExtra("EXTRA_TANGGAL", date)
            val currentDate = LocalDate.now()
            startIntent.launch(intent_tujuan)

        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .padding(top = 150.dp)
                .align(Alignment.CenterHorizontally)
                .width(170.dp)
            ) {
            Text(text = "DAFTAR")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun preview_main_content() {
    Main_app()
}