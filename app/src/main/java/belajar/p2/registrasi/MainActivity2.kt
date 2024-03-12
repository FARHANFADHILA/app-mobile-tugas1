package belajar.p2.registrasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import java.time.LocalDate
import belajar.p2.registrasi.ui.theme.RegistrasiTheme
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity2 : ComponentActivity() {
    var str_nama = ""
    var date = ""

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(text = "keterangan") },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                            )
                        }
                    ) {
                        str_nama = intent.getStringExtra("EXTRA_NAMA").toString()
                        date = intent.getStringExtra("EXTRA_TANGGAL").toString()
                        val umuR = umur(date)
                        Column(
                            modifier = Modifier
                                .padding(30.dp)
                                .padding(top = 190.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .height(50.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .padding(5.dp)
                                ) {
                                    Text(text = "Nama", modifier = Modifier.widthIn(190.dp), textAlign = TextAlign.Center
                                    )
                                    Text(text = str_nama.uppercase(), modifier = Modifier.widthIn(190.dp))
                                }

                            }
                            Card(
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(top = 2.dp)
                            ){
                                Row(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(text = "Tanggal Lahir", modifier = Modifier.widthIn(190.dp),textAlign = TextAlign.Center)
                                    Text(text = date, modifier = Modifier.widthIn(190.dp))
                                }

                            }
                            Card(
                                modifier = Modifier
                                    .height(100.dp)
                                    .padding(top = 2.dp)
                            ){
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(text = "Umur", modifier = Modifier.widthIn(190.dp),textAlign = TextAlign.Center)
                                    Text(text = umuR, modifier = Modifier.widthIn(min = 0.dp, max = 190.dp))
                                }

                            }
                        }

                    }

                }
            }
        }
    }
}

fun umur(date: String): String {
    val result = mutableListOf<String>()
    var currentNumber = StringBuilder()

    for (x in date) {
        if (x == '-') {
            result.add(currentNumber.toString())
            currentNumber.clear()
        } else {
            currentNumber.append(x)
        }
    }
    result.add(currentNumber.toString())
    var hari = result[0].toInt()
    var bulan = result[1].toInt()
    var tahun = result[2].toInt()
    val tanggal_lahir = LocalDate.of(tahun, bulan, hari)
    val tanggalSekarang = LocalDate.now()
    var tahun_result = tanggalSekarang.year - tanggal_lahir.year
    var bulan_result = tanggalSekarang.monthValue - tanggal_lahir.monthValue
    var hari_result = tanggalSekarang.dayOfMonth - tanggal_lahir.dayOfMonth
    if (bulan_result < 0){
        tahun_result-=1
        bulan_result+=12
    }
    if (hari_result < 0){
        bulan_result -=1
        hari_result +=30
    }
    return "$tahun_result TAHUN\n$bulan_result BULAN\n$hari_result HARI"

}





