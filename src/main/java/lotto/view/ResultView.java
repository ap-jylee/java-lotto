package lotto.view;

import lotto.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

import static lotto.domain.LottoRank.*;

public class ResultView {

    private static final String PURCHASE_COUNT_MSG_FORMAT = "%d개를 구매했습니다.";
    private static final String MATCH_NUMBER_COUNT_FORMAT = "%d개 일치 (%d%s)- %d개";
    private static final String NUMBERS_PRINT_FORMAT = "[%s]";
    private static final String EARNING_RATE_RESULT_FORMAT = "총 수익률을 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";

    private static final String LOTTO_RESULT_MSG = "당첨 통계";
    private static final String SEPERATE_LINE = "---------";
    public static final String LOTTO_NUM_DELIMITER = ", ";

    private static final String PROFIT = "이익";
    private static final String LOSS = "손해";
    private static final String PRINCIPAL = "본전";

    private static final BigDecimal PROFIT_POINT = BigDecimal.ONE;

    private ResultView() {}

    public static void print(LottoTicket lottoTicket) {
        System.out.printf(PURCHASE_COUNT_MSG_FORMAT, lottoTicket.size());
        printLineBreak();

        for (LottoNumbers lottoNumbers : lottoTicket.getLotteryNumbers()) {
            print(lottoNumbers);
        }

        printLineBreak();
    }

    private static void print(LottoNumbers lottoNumbers) {
        String lottoNumbersText = lottoNumbers.getOrderedNumbers()
                .stream()
                .map(number -> String.valueOf(number.getNumber()))
                .collect(Collectors.joining(LOTTO_NUM_DELIMITER));

        System.out.printf(NUMBERS_PRINT_FORMAT, lottoNumbersText);
        printLineBreak();
    }

    private static void printLineBreak() {
        System.out.println();
    }

    public static void printLottoResult(LottoResults results, Money inputMoney) {
        System.out.println(LOTTO_RESULT_MSG);
        System.out.println(SEPERATE_LINE);

        for (LottoRank lottoRank : Arrays.asList(FOURTH, THIRD, SECOND, FIRST)) {
            System.out.printf(MATCH_NUMBER_COUNT_FORMAT,
                    lottoRank.getMatchCount(), lottoRank.getPrizeMoney().getAmount().intValue(), Money.CURRENCY, results.countBy(lottoRank));
            printLineBreak();
        }

        BigDecimal earningRate = results.calculateEarningRate(inputMoney, 2, RoundingMode.FLOOR);

        System.out.printf(EARNING_RATE_RESULT_FORMAT,
                earningRate,
                getLottoResultMsg(earningRate));
    }

    private static String getLottoResultMsg(BigDecimal earningRate) {
        int point = earningRate.compareTo(PROFIT_POINT);

        if (point < 0) {
            return LOSS;
        }

        if (point == 0) {
            return PRINCIPAL;
        }

        return PROFIT;
    }
}
