package nl.avans.testhelper.parser;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.stmt.*;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class PMethod {

    private final List<MethodDeclaration> methods;
    private PClass pClass;

    PMethod(PClass pclass, List<MethodDeclaration> methods) {
        this.methods = methods;
        this.pClass = pclass;
    }

    public PMethod returningVoid()
    {
        return new PMethod(pClass, methods.stream()
                .filter(p -> p.getType().isVoidType()).collect(Collectors.toList()));
    }

    public PMethod returning(String type)
    {
        return new PMethod(pClass, methods.stream()
                .filter(p -> p.getTypeAsString().equals(type) ).collect(Collectors.toList()));
    }

    public PMethod noParams()
    {
        return new PMethod(pClass, methods.stream()
                .filter(p -> p.getParameters().size() == 0).collect(Collectors.toList()));
    }
    public PMethod parameters(String... params) {
        return new PMethod(pClass, methods.stream()
                .filter(p -> {
                    if(p.getParameters().size() != params.length)
                        return false;
                    for(int i = 0; i < p.getParameters().size(); i++)
                        if(!p.getParameters().get(i).getType().toString().equals(params[i]))
                            return false;
                    return true;
                }).collect(Collectors.toList()));
    }

    private void checkComplete() {
        Assert.assertTrue( this.methods.size() == 1);
    }

    public String getCode()
    {
        checkComplete();
        return methods.get(0).toString();
    }

    public MethodDeclaration getMethod()
    {
        return methods.get(0);
    }

    public void assertContains(Statement statement) {
        checkComplete();
        boolean contains = assertContainsRecursive(statement, methods.get(0).getChildNodes());
        if(!contains)
            Assert.fail("Your method does not contain a "+statement +" statement. The code to your method is \n" + this.getCode());
    }

    private boolean assertContainsRecursive(Statement statement, List<Node> childNodes) {
        for(Node n : childNodes) {

            if(n.getChildNodes().size() > 0)
            //if(n instanceof BlockStmt)
            {
              //  BlockStmt blockStmt = (BlockStmt)n;
                if(assertContainsRecursive(statement, n.getChildNodes()))
                    return true;
            }
            if(statement == Statement.ForEach && n instanceof ForEachStmt)
                return true;
            if(statement == Statement.For && n instanceof ForStmt)
                return true;
            if(statement == Statement.While && n instanceof WhileStmt)
                return true;
            if(statement == Statement.Super && n instanceof SuperExpr)
                return true;
        }
        return false;
    }


}
