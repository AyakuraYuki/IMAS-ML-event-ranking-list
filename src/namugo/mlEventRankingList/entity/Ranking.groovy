package namugo.mlEventRankingList.entity

import static java.lang.System.out

/**
 * Created by YukiAyakura on 2017-02-04.
 */
class Ranking {

    private String rank
    private String producer
    private String pt

    String getRank() {
        return rank
    }

    void setRank(String rank) {
        this.rank = rank
    }

    String getProducer() {
        return producer
    }

    void setProducer(String producer) {
        this.producer = producer
    }

    String getPt() {
        return pt
    }

    void setPt(String pt) {
        this.pt = pt
    }

    void display() {
        out.printf("%8s\t%-10s\t\t\t%20s\n", this.rank, this.producer, this.pt)
    }

    @Override
    String toString() {
        return rank + "\t\t" + producer + "\t\t" + pt
    }

}
