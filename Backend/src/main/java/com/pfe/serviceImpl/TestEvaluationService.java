package com.pfe.serviceImpl;

import com.pfe.dto.AnswerDTO;
import com.pfe.dto.TestEvaluationDTO;
import com.pfe.model.Answer;
import com.pfe.model.Question;
import com.pfe.model.Request;
import com.pfe.model.TestEvaluation;
import com.pfe.repository.AnswerRepository;
import com.pfe.repository.QuestionRepository;
import com.pfe.repository.RequestRespository;
import com.pfe.repository.TestEvaluationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestEvaluationService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestEvaluationRepository evaluationRepository;

    @Autowired
    private RequestRespository requestRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public TestEvaluation getEvaluationByRequestId(Integer requestId) {
        return evaluationRepository.findByRequestId(requestId)
                .orElse(null);
    }

    public TestEvaluation getEvolutionByCandidatIdAndRequestId(String candidatId , Integer requestId){
        return   evaluationRepository.findByCandidateIdAndRequestIdWithAnswers(candidatId,requestId);
    }

    public List<Answer> findAnswersByCandidateIdAndRequestId(String candidatId , Integer requestId){
        return   answerRepository.findAnswersByCandidateIdAndRequestId(candidatId,requestId);
    }

    public TestEvaluation submitEvaluation(TestEvaluationDTO submissionDTO) {
        Request request = requestRepository.findById(submissionDTO.getRequestId())
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Vérifier si une évaluation existe déjà pour cette demande
        TestEvaluation existingEvaluation = evaluationRepository.findByRequestId(request.getId())
                .orElse(null);

        if (existingEvaluation != null) {
            throw new RuntimeException("Une évaluation existe déjà pour cette demande");
        }

        TestEvaluation evaluation = new TestEvaluation();
        evaluation.setCandidateId(submissionDTO.getCandidateId());
        evaluation.setRequest(request);
        evaluation.setSubmissionDate(LocalDateTime.now());

        List<Answer> answers = new ArrayList<>();

        for (AnswerDTO answerDTO : submissionDTO.getAnswers()) {
            Answer answer = new Answer();
            answer.setQuestion(questionRepository.findById(answerDTO.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found")));
            answer.setRatingValue(answerDTO.getRatingValue());
            answer.setTextValue(answerDTO.getTextValue());
            answer.setEvaluation(evaluation);
            answers.add(answer);
        }

        evaluation.setAnswers(answers);
        return evaluationRepository.save(evaluation);
    }

    public List<TestEvaluation> getEvaluationsByCandidateId(String candidateId) {
        return evaluationRepository.findByCandidateId(candidateId);
    }
}