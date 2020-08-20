package step2.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoStatistics {

    private LottoStatistics() {

    }

    public static Map<Integer, Integer> calculateWinningCountStatistics(LottoResults lottoResults) {
        Map<Integer, Integer> result = new HashMap<>();
        IntStream.range(3, 7)
                .forEach(winningCount -> result.put(winningCount, lottoResults.calculateWinningNumbersCount(winningCount)));
        return result;
    }

    public static double calculateYield(Money money, LottoResults lottoResults) {
        return money.calculateYelid(lottoResults.calculateWinnings());
    }

}
