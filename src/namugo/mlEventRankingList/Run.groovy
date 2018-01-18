package namugo.mlEventRankingList

import namugo.mlEventRankingList.ui.GUIMain
import namugo.mlEventRankingList.utils.UpdateLogoUtils

class Run {

    static {
        UpdateLogoUtils.Update()
    }

    static main(args) {
        new GUIMain()
    }

}
