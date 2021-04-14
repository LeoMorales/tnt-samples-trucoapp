package unpsjb.ing.tnt.tnt2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import unpsjb.ing.tnt.tnt2021.databinding.ActivityEquiposBinding

const val TEAM_A_NAME = "unpsjb.tnt.EQUIPO_A"
const val TEAM_B_NAME = "unpsjb.tnt.EQUIPO_B"


class NewGameActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEquiposBinding
    private val TAG = "EquiposActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "team name: " + binding.editTextTeamB.editableText)
        binding.button.setOnClickListener {
            val teamA_name = binding.editTextTeamA.editableText.toString()
            val teamB_name = binding.editTextTeamB.editableText.toString()
            Log.i(TAG, "team A name: " + teamA_name)
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra(TEAM_A_NAME, teamA_name)
                putExtra(TEAM_B_NAME, teamB_name)
            }

            startActivity(intent)
        }

    }
}