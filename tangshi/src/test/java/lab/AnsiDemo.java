package lab;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.junit.Test;

import java.util.List;

/**
 * 分词Demo测试
 */
public class AnsiDemo {
    @Test
    public void splitTest(){
        String sentence = "愿你熬得过万丈孤独，藏得下星辰大海";
        List<Term> termList = NlpAnalysis.parse(sentence).getTerms();
        for (Term term : termList) {
            //getNatureStr输出词性，getRealName输出词语
            System.out.println(term.getNatureStr() + ":" + term.getRealName());
        }

    }
}
