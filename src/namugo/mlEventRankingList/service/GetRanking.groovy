package namugo.mlEventRankingList.service

import namugo.mlEventRankingList.entity.Ranking
import namugo.mlEventRankingList.utils.TaskUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GetRanking {

    private static final Logger logger = LogManager.getLogger(GetRanking.class)

    static List<Ranking> getRankingList() {
        List<Ranking> list = new ArrayList<>()
        logger.info("Create a new ranking list ----------Done")

        TaskUtils[] tasks = new TaskUtils[13]
        logger.info("Query ranking list by using multiple task threads.")

        tasks[0] = new TaskUtils(1)
        tasks[1] = new TaskUtils(2)
        for (int i = 2; i < tasks.length - 1; i++)
            tasks[i] = new TaskUtils((i - 1) * 10)
        tasks[12] = new TaskUtils(120)

        for (TaskUtils task : tasks)
            list.addAll(task.call())
        logger.info("Packaging ----------Done")

        return list
    }

}
