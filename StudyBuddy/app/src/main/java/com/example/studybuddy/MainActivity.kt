package com.example.studybuddy

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import com.example.studybuddy.ui.theme.StudyBuddyTheme
import com.example.studybuddy.view.NavGraphs
import com.example.studybuddy.view.destinations.SessionScreenRouteDestination
import com.example.studybuddy.view.session.StudySessionTimerService
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isBound by mutableStateOf(false)
    private lateinit var timerService: StudySessionTimerService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as StudySessionTimerService.StudySessionTimerBinder
            timerService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, StudySessionTimerService::class.java).also { intent ->
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {


                // Only navigate when the service is bound
                if (isBound) {
                    StudyBuddyTheme {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            dependenciesContainerBuilder = {
                                dependency ( SessionScreenRouteDestination ) {timerService
                                }
                            }
                        )
                    }
                } else {
                    // Show a loading UI or a message while the service is binding
                    LoadingScreen() // Custom composable to show a loading indicator
                }
            }


        requestPermission()
    }
    private fun requestPermission(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU)
        {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0)
        }
    }

    override fun onStop() {
        super.onStop()
        // Keep the service bound to maintain the session
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}

@Composable
fun LoadingScreen() {
    // This is a placeholder UI to display while waiting for the service to bind
    Text("Loading...")
}
