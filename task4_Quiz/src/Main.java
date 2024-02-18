import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

 class QuizApplication {
    private static final int QUESTION_TIME_LIMIT_SECONDS = 15;
    private static final int TOTAL_QUESTIONS = 6;
    private static final int QUESTION_BANK_SIZE = 10;

    private static int score = 0;
    private static int currentQuestionIndex = 0;

    private static List<Question> questionBank = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        initializeQuestionBank();
        startQuiz();
    }

    private static void initializeQuestionBank() {

        questionBank.add(new Question("What is the capital of Scotland?",
                new String[]{"A. Edinburgh", "B. London", "C. Paris", "D. Madrid"}, "A"));
        questionBank.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"A. Jupiter", "B. Mars", "C. Venus", "D. Saturn"}, "B"));
        questionBank.add(new Question("What is the chemical symbol for water?",
                new String[]{"A.CaCO3 ", "B. H2SO4", "C. H2O", "D. CaCl2"}, "C"));
        questionBank.add(new Question("Who wrote 'One Piece'?",
                new String[]{"A.Eiichiro Oda", "B. Charles Dickens", "C. Jane Bullet", "D. Mark Twain"}, "A"));
        questionBank.add(new Question("Which is the Fastest mammal?",
                new String[]{"A. Cheetah", "B. Elephant", "C. Hippopotamus", "D. Lion"}, "A"));
        questionBank.add(new Question("Which company does Camaro Car belongs to?",
                new String[]{"A. Mustang", "B. Maserati", "C. Chevrolet", "D. Bentley"}, "C"));
        // Add more questions as needed
    }

    private static void startQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have " + QUESTION_TIME_LIMIT_SECONDS + " seconds to answer each question.");

        
        Random rand = new Random();
        for (int i = 0; i < questionBank.size(); i++) {
            int randomIndex = rand.nextInt(questionBank.size());
            Question temp = questionBank.get(i);
            questionBank.set(i, questionBank.get(randomIndex));
            questionBank.set(randomIndex, temp);
        }

        
        askQuestion();
    }

    private static void askQuestion() {
        if (currentQuestionIndex < TOTAL_QUESTIONS) {
            Question currentQuestion = questionBank.get(currentQuestionIndex);
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            // Start timer for question
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    checkAnswer(null);
                }
            };
            timer.schedule(task, QUESTION_TIME_LIMIT_SECONDS * 1000);

            System.out.print("Your answer (A, B, C, or D): ");
            String userAnswer = scanner.nextLine().toUpperCase();

            // Cancel the timer as the user has submitted their answer
            task.cancel();

            checkAnswer(userAnswer);
        } else {
            endQuiz();
        }
    }

    private static void checkAnswer(String userAnswer) {
        Question currentQuestion = questionBank.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer();
        System.out.println("\nCorrect answer: " + correctAnswer);

        if (userAnswer != null && userAnswer.equals(correctAnswer)) {
            System.out.println("Your answer is correct!");
            score++;
        } else {
            System.out.println("Your answer is incorrect!");
        }

        currentQuestionIndex++;
        askQuestion();
    }

    private static void endQuiz() {
        System.out.println("\nQuiz ended!");
        System.out.println("Your final score: " + score + " out of " + TOTAL_QUESTIONS);

        System.out.println("\nSummary of Answers:");
        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            Question question = questionBank.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Correct Answer: " + question.getCorrectAnswer());
            System.out.println("Your Answer: " + (i < score ? "Correct" : "Incorrect"));
            System.out.println();
        }

        scanner.close();
        timer.cancel();
    }

    static class Question {
        private String question;
        private String[] options;
        private String correctAnswer;

        public Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
