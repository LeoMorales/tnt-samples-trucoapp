package unpsjb.ing.tnt.tnt2021

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import unpsjb.ing.tnt.tnt2021.databinding.TableroCompletoBinding


const val SCORE_TEAM_A = "unpsjb.tnt.EQUIPO_A_SCORE"
const val SCORE_TEAM_B = "unpsjb.tnt.EQUIPO_B_SCORE"
const val NAME_TEAM_A = "unpsjb.tnt.EQUIPO_A_NAME"
const val NAME_TEAM_B = "unpsjb.tnt.EQUIPO_B_NAME"

class GameActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var binding: TableroCompletoBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var teamA_name: String
    private lateinit var teamB_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TableroCompletoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stateViewModel = ViewModelProvider(this).get(
            StateViewModel::class.java
        )
        connectButtonsEvents()
        paintScoreA(stateViewModel.scoreTeamA)
        paintScoreB(stateViewModel.scoreTeamA)

        binding.titleLabel.setOnClickListener {
            val intent = Intent(this, FinalResultActivity::class.java)
            startActivity(intent)
        }

        // Get the Intent that started this activity and extract the string
        teamA_name = intent.getStringExtra(TEAM_A_NAME)
        teamB_name = intent.getStringExtra(TEAM_B_NAME)
        binding.team1Label.text = teamA_name
        binding.team2Label.text = teamB_name

    }

    private fun incrementTeamA() {
        // incrementar y pintar
        //score1 ++
        stateViewModel.pointToA()
        paintScoreA(stateViewModel.scoreTeamA)
    }

    private fun connectButtonsEvents() {
        binding.inc1Button.setOnClickListener {
            incrementTeamA()
            if (stateViewModel.scoreTeamA == 15){
                Toast.makeText(
                        this,
                        "${teamA_name} llegó a las buenas!",
                        Toast.LENGTH_LONG)
                        .show()
                stateViewModel.isInSecondRoundA()
            }

            if (stateViewModel.scoreTeamA == 30)
                showFinalResult()

        }

        binding.inc2Button.setOnClickListener {
            incrementTeamB()
            if (stateViewModel.scoreTeamB == 15){
                Toast.makeText(
                        this,
                        "${teamB_name} llegó a las buenas!",
                        Toast.LENGTH_LONG)
                        .show()
                stateViewModel.isInSecondRoundB()
            }

            if (stateViewModel.scoreTeamB == 30)
                showFinalResult()

        }

        binding.decAButton1.setOnClickListener {
            decrementTeamA()
        }
        binding.decAButton2.setOnClickListener {
            decrementTeamA()
        }
        binding.decAButton3.setOnClickListener {
            decrementTeamA()
        }
        binding.decBButton1.setOnClickListener {
            decrementTeamB()
        }
        binding.decBButton2.setOnClickListener {
            decrementTeamB()
        }
        binding.decBButton3.setOnClickListener {
            decrementTeamB()
        }

        binding.imageButtonShareResult.setOnClickListener {
            move_to_next_screen()
        }

    }

    private fun showFinalResult() {
        binding.imageButtonShareResult.visibility = View.VISIBLE

        binding.textViewFinalResultLabel.visibility = View.VISIBLE
        binding.textViewFinalResultLabel.text = "${stateViewModel.scoreTeamA} - ${stateViewModel.scoreTeamB}"

        binding.inc1Button.isEnabled = false
        binding.inc2Button.isEnabled = false
        binding.decAButton1.isEnabled = false
        binding.decAButton2.isEnabled = false
        binding.decAButton3.isEnabled = false
        binding.decBButton1.isEnabled = false
        binding.decBButton2.isEnabled = false
        binding.decBButton3.isEnabled = false

    }

    private fun move_to_next_screen() {
        val intent = Intent(this, FinalResultActivity::class.java).apply {
            putExtra(SCORE_TEAM_A, stateViewModel.scoreTeamA.toString())
            putExtra(SCORE_TEAM_B, stateViewModel.scoreTeamB.toString())
            putExtra(NAME_TEAM_A, teamA_name)
            putExtra(NAME_TEAM_B, teamB_name)
        }
        startActivity(intent)

    }


    private fun paintScoreA(score: Int) {
        var result = splitScore(score)
        drawScoreAImages(result)
        turnOnSubstractButtonForIndex(getIndexOfSubstractButton(score))

    }



    private fun incrementTeamB() {
        stateViewModel.pointToB()
        paintScoreB(stateViewModel.scoreTeamB)
    }

    private fun decrementTeamA() {
        stateViewModel.decreaseTeamA()
        paintScoreA(stateViewModel.scoreTeamA)
    }
    private fun decrementTeamB() {
        stateViewModel.decreaseTeamB()
        paintScoreB(stateViewModel.scoreTeamB)
    }

    private fun paintScoreB(score: Int) {
        var result = splitScore(score)
        drawScoreBImages(result)
        turnOnSubstractButtonForIndex_B(getIndexOfSubstractButton(score))
    }

    private fun drawScoreAImages(result: java.util.ArrayList<Int>) {
        binding.scoreA1ImageView.setImageResource(getResourceForNumber(result[0]))
        binding.scoreA2ImageView.setImageResource(getResourceForNumber(result[1]))
        binding.scoreA3ImageView.setImageResource(getResourceForNumber(result[2]))

    }

    private fun drawScoreBImages(result: java.util.ArrayList<Int>) {
        binding.scoreB1ImageView.setImageResource(getResourceForNumber(result[0]))
        binding.scoreB2ImageView.setImageResource(getResourceForNumber(result[1]))
        binding.scoreB3ImageView.setImageResource(getResourceForNumber(result[2]))

    }

    private fun turnOnSubstractButtonForIndex(index: Int) {
        binding.decAButton1.isVisible = false
        binding.decAButton2.isVisible = false
        binding.decAButton3.isVisible = false
        if (index == 1 )
            binding.decAButton1.isVisible = true
        if (index == 2 )
            binding.decAButton2.isVisible = true
        if (index == 3 )
            binding.decAButton3.isVisible = true
    }

    private fun turnOnSubstractButtonForIndex_B(index: Int) {
        binding.decBButton1.isVisible = false
        binding.decBButton2.isVisible = false
        binding.decBButton3.isVisible = false
        if (index == 1 )
            binding.decBButton1.isVisible = true
        if (index == 2 )
            binding.decBButton2.isVisible = true
        if (index == 3 )
            binding.decBButton3.isVisible = true
    }

    private fun getResourceForNumber(number: Int): Int {
        var resursoImg = when (number){
            1 -> R.drawable.ic_tantos_1
            2 -> R.drawable.ic_tantos_2
            3 -> R.drawable.ic_tantos_3
            4 -> R.drawable.ic_tantos_4
            5 -> R.drawable.ic_tantos_5
            else -> R.drawable.ic_tantos_vacio
        }

        return resursoImg

    }

    private fun splitScore(score: Int): ArrayList<Int> {
        var scoreToDraw = score
        if (score > 15)
            scoreToDraw = score - 15

        if(scoreToDraw < 5 )
            return arrayListOf(scoreToDraw, 0, 0)
        if(scoreToDraw < 10 )
            return arrayListOf(5, scoreToDraw - 5, 0)
        if(scoreToDraw < 15 )
            return arrayListOf(5, 5, scoreToDraw - 10)

        return arrayListOf(5, 5, 5)
    }

    private fun getIndexOfSubstractButton(score: Int): Int {
        return when (score){
            in 1..5 -> 1
            in 6..10 -> 2
            in 11..15 -> 3
            in 16..20 -> 1
            in 21..25 -> 2
            in 26..30 -> 3
            else -> 0
        }
    }


}