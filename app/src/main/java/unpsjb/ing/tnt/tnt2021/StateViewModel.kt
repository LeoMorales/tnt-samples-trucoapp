package unpsjb.ing.tnt.tnt2021

import androidx.lifecycle.ViewModel

class StateViewModel : ViewModel() {
    // Mantener el puntaje del equipo A
    var scoreTeamA = 0
    var inSecondRoundA = false

    // Mantener el puntaje del equipo B
    var scoreTeamB = 0
    var inSecondRoundB = false

    fun pointToA(){
        scoreTeamA += 1
    }

    fun pointToB(){
        scoreTeamB += 1
    }

    fun decreaseTeamA(){
        scoreTeamA -= 1
    }

    fun decreaseTeamB(){
        scoreTeamB -= 1
    }

    fun resetScoreA(){
        scoreTeamA = 0
    }

    fun isInSecondRoundA() {
        inSecondRoundA = true
    }

    fun isInSecondRoundB() {
        inSecondRoundB = true
    }
}
