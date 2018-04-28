package GUI;

public class Cable {
    BlockComponent start;
    BlockComponent end;

    public void Reverse()
    {
        BlockComponent temp = start;
        start = end;
        end = temp;
    }
}
