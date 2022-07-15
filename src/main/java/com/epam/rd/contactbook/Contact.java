package com.epam.rd.contactbook;

public class Contact {
    private String contactName;
    NameContactInfo nameContactInfo;
    private final Email[] emails = new Email[3];
    private final Social[] socialMedias = new Social[5];
    ContactInfo phoneNumber;
    private int numberOfEmail, numberOfSocials;


    public Contact(String contactName) {
        this.contactName=contactName;
        nameContactInfo=new NameContactInfo();
    }

    private class NameContactInfo implements ContactInfo{
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return contactName;
        }
    }
    public static class Email implements ContactInfo{
        String email;

        public void setEmail(String name, String domain) {
            this.email = name + "@" + domain;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo{
        private final String social;
        private String title;

        public Social(String social) {
            this.social = social;
        }

        public Social(String social, String title) {
            this.social=social;
            this.title = title;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return social;
        }
    }

    public void rename(String newName) {
        if (newName!=null && newName.length()!=0)
            this.contactName=newName;
    }

    public Email addEmail(String localPart, String domain) {
        if (localPart==null || domain==null || numberOfEmail>2)
            return null;
        emails[numberOfEmail]=new Email();
        emails[numberOfEmail].setEmail(localPart,domain);
        return emails[numberOfEmail++];
    }


    public Email addEpamEmail(String firstname, String lastname) {
        if (firstname==null || lastname==null || numberOfEmail>2)
            return null;
        emails[numberOfEmail]=new Email(){
            @Override
            public String getTitle() {
                return "Epam Email";
            }

            @Override
            public void setEmail(String firstname, String lastname){
                email = firstname + "_" + lastname + "@epam.com";
            }
        };
        emails[numberOfEmail].setEmail(firstname, lastname);
        return emails[numberOfEmail++];
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (number==null || phoneNumber!=null)
            return null;

        phoneNumber=new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+" + code + " " +number;
            }
        };
        return phoneNumber;
    }

    public Social addTwitter(String twitterId) {
        if (twitterId==null || numberOfSocials>4)
            return null;

        socialMedias[numberOfSocials]=new Social(twitterId){
            @Override
            public String getTitle() {
                return "Twitter";
            }
        };
        return socialMedias[numberOfSocials++];
    }

    public Social addInstagram(String instagramId) {
        if (instagramId==null || numberOfSocials>4)
            return null;

        socialMedias[numberOfSocials] = new Social(instagramId){
            @Override
            public String getTitle() {
                return "Instagram";
            }
        };
        return socialMedias[numberOfSocials++];
    }

    public Social addSocialMedia(String title, String id) {
        if (title==null || id==null || numberOfSocials>4)
            return null;

        socialMedias[numberOfSocials] = new Social(id, title);
        return socialMedias[numberOfSocials++];
    }

    public ContactInfo[] getInfo() {
        if (contactName==null)
            return null;
        int i=0;
        int length=1+(phoneNumber!=null?1:0)+numberOfEmail+numberOfSocials;
        ContactInfo[] output = new ContactInfo[length];
        output[i++]=nameContactInfo;
        if (phoneNumber!=null) {
            output[i++] = phoneNumber;
        }
        for (int j=0; j< numberOfEmail;j++) {
            output[i++] = emails[j];
        }
        for (int j=0; j< numberOfSocials;j++) {
            output[i++] = socialMedias[j];
        }
        return output;
    }

}
