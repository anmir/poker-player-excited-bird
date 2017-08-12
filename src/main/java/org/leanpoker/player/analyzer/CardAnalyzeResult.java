package org.leanpoker.player.analyzer;

import org.leanpoker.player.constants.Combination;

/**
 * Created by andrey on 12.08.17.
 */
public class CardAnalyzeResult {
    private Combination combination;
    private Integer biggestCardInCombination;

    @java.beans.ConstructorProperties({"combination", "biggestCardInCombination"})
    public CardAnalyzeResult(Combination combination, Integer biggestCardInCombination) {
        this.combination = combination;
        this.biggestCardInCombination = biggestCardInCombination;
    }

    public Combination getCombination() {
        return this.combination;
    }

    public Integer getBiggestCardInCombination() {
        return this.biggestCardInCombination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    public void setBiggestCardInCombination(Integer biggestCardInCombination) {
        this.biggestCardInCombination = biggestCardInCombination;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CardAnalyzeResult)) return false;
        final CardAnalyzeResult other = (CardAnalyzeResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$combination = this.getCombination();
        final Object other$combination = other.getCombination();
        if (this$combination == null ? other$combination != null : !this$combination.equals(other$combination))
            return false;
        final Object this$biggestCardInCombination = this.getBiggestCardInCombination();
        final Object other$biggestCardInCombination = other.getBiggestCardInCombination();
        if (this$biggestCardInCombination == null ? other$biggestCardInCombination != null : !this$biggestCardInCombination.equals(other$biggestCardInCombination))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $combination = this.getCombination();
        result = result * PRIME + ($combination == null ? 43 : $combination.hashCode());
        final Object $biggestCardInCombination = this.getBiggestCardInCombination();
        result = result * PRIME + ($biggestCardInCombination == null ? 43 : $biggestCardInCombination.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CardAnalyzeResult;
    }

    public String toString() {
        return "org.leanpoker.player.analyzer.CardAnalyzeResult(combination=" + this.getCombination() + ", biggestCardInCombination=" + this.getBiggestCardInCombination() + ")";
    }
}
