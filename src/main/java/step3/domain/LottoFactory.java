package step3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    public static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int PICK_COUNT = 6;
    private static final List<Integer> LOTTO_NUMBERS = IntStream.rangeClosed(FIRST_LOTTO_NUMBER, LAST_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    public static List<Lotto> create(int lottoPurchaseCount) {
        return IntStream.range(0, lottoPurchaseCount)
                .mapToObj(i -> new Lotto(pickLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static List<Integer> pickLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return new ArrayList<>(LOTTO_NUMBERS.subList(0, PICK_COUNT));
    }

}
