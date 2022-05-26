package draw.stroke;

import java.awt.BasicStroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class CustomStroke extends BasicStroke implements Serializable {

  private static class Serial implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient CustomStroke replacement;

    Serial(CustomStroke replacement) {
      this.replacement = replacement;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
      objectOutputStream.writeFloat(replacement.getLineWidth());
      objectOutputStream.writeInt(replacement.getEndCap());
      objectOutputStream.writeInt(replacement.getLineJoin());
      objectOutputStream.writeFloat(replacement.getMiterLimit());
      objectOutputStream.writeUnshared(replacement.getDashArray());
      objectOutputStream.writeFloat(replacement.getDashPhase());
    }

    private void readObject(ObjectInputStream objectInputStream)
        throws IOException, ClassNotFoundException {
      this.replacement = new CustomStroke(objectInputStream.readFloat(),
          objectInputStream.readInt(), objectInputStream.readInt(),
          objectInputStream.readFloat(), (float[]) objectInputStream.readUnshared(),
          objectInputStream.readFloat());
    }

    private Object readResolve() {
      return replacement;
    }
  }

  public static BasicStroke serializable(BasicStroke target) {
    return (target instanceof Serializable) ? target
        : new CustomStroke(target.getLineWidth(), target.getEndCap(), target.getLineJoin(),
            target.getMiterLimit(), target.getDashArray(), target.getDashPhase());
  }

  public CustomStroke() {
    super();
  }

  public CustomStroke(float lineWidth) {
    super(lineWidth);
  }

  public CustomStroke(float lineWidth, int endCap, int lineJoin, float miterLimit,
      float[] dashArray, float dashPhase) {
    super(lineWidth, endCap, lineJoin, miterLimit, dashArray, dashPhase);
  }

  private Object writeReplace() throws ObjectStreamException {
    return new Serial(this);
  }
}
