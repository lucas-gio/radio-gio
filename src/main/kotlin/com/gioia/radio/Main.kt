import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gioia.radio.services.DatabaseService

@Composable
@Preview
fun MainWindow() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)){
            Finders(
                modifier = Modifier.weight(0.5f),
                text = "",
                onTextChanged = {}
            )
        }
    }
}

@Composable
fun Finders(
    text:String,
    modifier: Modifier = Modifier,
    onTextChanged: (countryName: String)-> Unit
){
    Column(modifier = modifier){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            label = {
                Text(text = "Nombre de país")
            }
        )
        //Spacer(Modifier.height(2.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            label = {
                Text(text = "Nombre de la radio")
            }
        )
    }
}

@Composable
fun Earth(){}

@Composable
fun Images(){}

@Composable
fun Player(){}

@Composable
fun RadioDetails(){}

fun main() = application {
    val DEFAULT_HEIGHT = 500
    val DEFAULT_WIDTH = 900
    try {
        DatabaseService.insertDefaultData()
        //Exporter exporter = Exporter.of(DatabaseService.db()); //fixme: Los datos iniciales deben venir de un
        // txt con las emisoras. Luego, cada cierto tiempo, se hace una sincronizaciónñ. Ver si es mejor esta sincr.
        // con la herramienta de esta db. o ssi es mejor con un serv. rest-.
        //exporter.exportTo("c:\\dataExported.txt");
        Window(onCloseRequest = ::exitApplication) {
            MainWindow()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        DatabaseService.close()
    }
}
