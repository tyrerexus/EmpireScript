package EmpireScript;

import java.util.Stack;

/**
 * ScriptRuntime is used to load and run EmpireScript scripts.
 *
 * @author Tyrerexus
 * @date 7/18/17
 */
public class ScriptRuntime {
    /**
     * ScriptRuntime will talk to the outer world through this interface.
     */
    public WorldInterface worldInterface;


    /**
     * The instructions that are to be ran by this runtime.
     */
    public InstructionBase[] instructions = null;


    /**
     * The value stack of this runtime.
     */
    public Stack<ScriptValue> stack = new Stack<>();


    /**
     * The call stack of this runtime.
     */
    public Stack<Integer> callStack = new Stack<>();


    /**
     * Which instruction is to be ran the next time step() is called.
     */
    public int programCounter = 0;


    /**
     * If the runtime has been stopped.
     */
    public boolean stopped;


    /**
     * Creates a runtime with a specific worldInterface.
     *
     * @param worldInterface ScriptRuntime will talk to the outer world through this interface.
     */
    public ScriptRuntime(WorldInterface worldInterface) {
        this.worldInterface = worldInterface;
    }


    /**
     * Takes a step and executes the current instruction pointed by programCounter.
     * <p>
     * Nothing will happen if either no instruction has been loaded or if stopped is set to true.
     */
    public void step() {
        if (!stopped && instructions != null && programCounter >= 0 && programCounter < instructions.length) {
            instructions[programCounter].execute(this);
            programCounter++;
        }
    }


    /**
     * Parses and loads a script into the instructions array from the argument given.
     *
     * @param script A string containing the script's source code.
     * @return False on failure.
     */
    public boolean loadScript(String script) {
        instructions = new Parser(worldInterface, script).parse();
        return instructions != null;
    }


    /**
     * A helper function for easily pushing a value onto the value stack.
     *
     * @param value The value to push onto the stack.
     */
    public void push(double value) {
        stack.add(new ValueNumber(value));
    }


    /**
     * A helper function for easily pushing a value onto the value stack.
     *
     * @param value The value to push onto the stack.
     */
    public void push(String value) {
        stack.add(new ValueArray(value));
    }

    /**
     * A helper function for easily pushing a value onto the value stack.
     *
     * @param value The value to push onto the stack.
     */
    public void push(ValueArray value) {
        stack.add(value);
    }
}
