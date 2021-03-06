package com.gxn.diamond.dao;

import com.gxn.diamond.domain.model.OptionItem;
import com.gxn.diamond.domain.model.QResult;
import com.gxn.diamond.domain.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IQuestionDAO {
    @Select({"select * from questionair.questions where id in(${questionIds}) ORDER BY FIELD(id,${questionIds})"})
    List<Question> getQuestionsById(@Param("questionIds") String questionIds);

    @Select({"select * from questionair.option_item where question_id =#{questionId} order by id"})
    List<OptionItem> getOptionItemsByQuestion(@Param("questionId") int questionId);


    @Insert({"Replace into questionair.result_data (" +
            "age,"+
            "gender,"+
            "smoke,"+
            "gaoxueya,"+
            "tangniaobing,"+
            "eye,"+
            "height,"+
            "weight,"+
            "hba1c,"+
            "sbp,"+
            "hdlc,"+
            "tg,"+
            "ldlc,"+
            "uacr,"+
            "scr,"+
            "sua,"+
            "phone,"+
            "openId,"+
            "bmi,"+
            "score,"+
            "modified," +
            "created" +
            ") " +
            "values(#{age},#{gender},#{smoke},#{gaoxueya},#{tangniaobing},#{eye},#{height},#{weight},#{hba1c},#{sbp},#{hdlc},#{tg},#{ldlc},#{uacr},#{scr},#{sua},#{phone},#{openId},#{bmi},#{score},now(),now())"})
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int saveResult(QResult qResult);

    @Select({"select * from questionair.result_data"})
    List<QResult> getAllResults();


}
