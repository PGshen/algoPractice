package world.playtogether.designpatterns.behavior;

/**
 * <project> algoPractice
 *
 * <p> 迭代器模式
 * 提供一种方法顺序访问一个聚合对象中各个元素, 而又无须暴露该对象的内部表示。
 *
 * @author penggs
 * @since 2020-10-25
 */
public class ApIterator {
    interface Iterator {
        boolean hasNext();
        Object next();
    }

    interface Container {
        Iterator getIterator();
    }

    static class NameRepository implements Container {
        public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator {
            int index;
            @Override
            public boolean hasNext() {
                return index < names.length;
            }

            @Override
            public Object next() {
                if(this.hasNext()){
                    return names[index++];
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}