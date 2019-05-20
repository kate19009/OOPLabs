public interface Colored
{
    default void setColor(Color color);

    Color getColor();

    void whoAmI();

    default String getColor(Color color)
    {

        switch (color.toString())
        {
            case ""java.awt.Color[r = 0, g = 255, b = 0]"":
                return "GREEN";

            default:
                return color.toString();
        }
    }
}
