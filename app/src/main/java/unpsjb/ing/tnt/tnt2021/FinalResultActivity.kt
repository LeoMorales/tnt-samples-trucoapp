package unpsjb.ing.tnt.tnt2021

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ShareCompat
import unpsjb.ing.tnt.tnt2021.databinding.ActivityHistoryBinding
import unpsjb.ing.tnt.tnt2021.databinding.TableroCompletoBinding

class FinalResultActivity : Activity() {
    private lateinit var binding: ActivityHistoryBinding
    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var teamA_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teamA_score = intent.getStringExtra(SCORE_TEAM_A)
        val teamB_score = intent.getStringExtra(SCORE_TEAM_B)

        teamA_name = intent.getStringExtra(NAME_TEAM_A).toString()
        binding.textViewTeamNameA.text = teamA_name
        binding.textViewScore.text = teamA_score+" - "+teamB_score
        binding.textViewTeamNameB.text = intent.getStringExtra(NAME_TEAM_B).toString()
        binding.imageButtonShareResult2.setOnClickListener { share_match_result() }
        binding.imageButtonTakePhoto.setOnClickListener { dispatchTakePictureIntent() }

    }

    fun share_match_result(){
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText("Resultado de la partida: ${teamA_name}")
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this, "ActivityNotFoundException",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageViewWinnerTeam.setImageBitmap(imageBitmap)
        }
    }

}