package EmpireScript;

/**
 * @author Tyrerexus
 * @date 7/18/17
 */
public class InstructionConcat extends InstructionBase {

    int amountToRead = 0;

    @Override
    void setArgs(String arg) {
        amountToRead = Integer.parseInt(arg);
    }

    @Override
    void execute(ScriptRuntime runtime) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < amountToRead; i++)
        {
            if (runtime.stack.isEmpty()) {
                reportError(runtime, "Stack is empty!");
                return;
            }
            else {
                builder.append(runtime.stack.pop().toString());
            }
        }
        runtime.push(builder.toString());
    }
}