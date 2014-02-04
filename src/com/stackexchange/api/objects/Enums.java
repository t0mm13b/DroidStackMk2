package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

public class Enums {
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum PostType{
		Question("Question"),
		Answer("Answer"),
		Unknown("Unknown");
		private String mFriendlyName;
		private PostType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum UserType{
		Unregistered("unregistered"),
		Registered("registered"),
		Moderator("moderator"),
		DoesNotExist("does_not_exist"),
		Unknown("Unknown");
		private String mFriendlyName;
		private UserType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum BadgeType{
		Named("named"),
		TagBased("tag_based"),
		Unknown("Unknown");
		private String mFriendlyName;
		private BadgeType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum BadgeRank{
		Gold("gold"),
		Silver("silver"),
		Bronze("bronze"),
		Unknown("unknown");
		private String mFriendlyName;
		private BadgeRank(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum Relation{
		Parent("parent"),
		Meta("meta"),
		Chat("chat"),
		Unknown("unknown");
		private String mFriendlyName;
		private Relation(String name){this.mFriendlyName = name; }
//		private static final Map<String, Relation> strToEnum = new HashMap<String, Relation>();
//		static{
//			for(Relation rel : values()){
//				strToEnum.put(rel.toString(), rel);
//			}
//			//intToEnum.putAll(EnumSet.A)
//		}
//		// faster lookup!
//		public static Relation valueOf(String value){
//			/*for(Relation rel : values()){
//				if (rel.mValue == value) return rel;
//			}
//			return Unknown;*/
//			if (strToEnum.containsKey(value) return intToEnum.get(Integer.valueOf(value));
//			return Unknown;
//		}
		
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SiteState{
		@SerializedName("normal") Normal,
		@SerializedName("closed_beta") ClosedBeta,
		@SerializedName("open_beta") OpenBeta,
		@SerializedName("linked_meta") LinkedMeta,
		Unknown
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SiteType{
		@SerializedName("main_site") MainSite,
		@SerializedName("meta_site") MetaSite,
		Unknown
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum EventType{
		QuestionPosted("question_posted"),
		AnswerPosted("answer_posted"),
		CommentPosted("comment_posted"),
		PostEdited("post_edited"),
		UserCreated("user_created"),
		Unknown("unknown");
		private String mFriendlyName;
		private EventType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum InboxType{
		Comment("comment"),
		ChatMessage("chat_message"),
		NewAnswer("new_answer"),
		CareersMessage("careers_message"),
		CareersInvitation("careers_invitation"),
		MetaQuestion("meta_question"),
		PostNotice("post_notice"),
		ModeratorMessage("moderator_message"),
		Unknown("unknown");
		private String mFriendlyName;
		private InboxType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum NotificationType{
		Generic("generic"),
		ProfileActivity("profile_activity"),
		BountyExpired("bounty_expired"),
		BountyExpires1D("bounty_expires_in_one_day"),
		BadgeEarned("badge_earned"),
		BountyExpires3D("bounty_expires_in_three_days"),
		ReputationBonus("reputation_bonus"),
		AccountsAssociated("accounts_associated"),
		NewPrivilege("new_privilege"),
		PostMigrated("post_migrated"),
		ModeratorMessage("moderator_message"),
		RegistrationReminder("registration_reminder"),
		EditSuggested("edit_suggested"),
		SubstantiveEdit("substantive_edit"),
		BountyGracePeriodStarted("bounty_grace_period_started"),
		Unknown("unknown");
		private String mFriendlyName;
		private NotificationType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum TimelineType{
		Question("question"),
		Answer("answer"),
		Comment("comment"),
		UnacceptedAnswer("unaccepted_answer"),
		AcceptedAnswer("accepted_answer"),
		VoteAggregate("vote_aggregate"),
		Revision("revision"),
		PostStateChanged("post_state_changed"),
		Commented("commented"),
		Asked("asked"),
		Answered("answered"),
		Badge("badge"),
		Accepted("accepted"),
		Reviewed("reviewed"),
		Suggested("suggested"),
		Unknown("unknown");
		private String mFriendlyName;
		private TimelineType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum VoteType{
		Accepts("accepts"),
		UpVotes("up_votes"),
		DownVotes("down_votes"),
		BountiesOffered("bounties_offered"),
		BountiesWon("bounties_won"),
		Spam("spam"),
		SuggestedEdits("suggested_edits"),
		Unknown("unknown");
		private String mFriendlyName;
		private VoteType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum ReputationHistoryType{
		AskerAcceptsAnswer("asker_accepts_answer"),
		AskerUnacceptAnswer("asker_unaccept_answer"),
		AnswerAccepted("answer_accepted"),
		AnswerUnaccepted("answer_unaccepted"),
		VoterDownVotes("voter_downvotes"),
		VoterUndownVotes("voter_undownvotes"),
		PostDownvoted("post_downvoted"),
		PostUndownVoted("post_undownvoted"),
		PostUpvoted("post_upvoted"),
		PostUnupVoted("post_unupvoted"),
		SuggestedEditApprovalReceived("suggested_edit_approval_received"),
		PostFlaggedAsSpam("post_flagged_as_spam"),
		PostFlaggedAsOffensive("post_flagged_as_offensive"),
		BountyGiven("bounty_given"),
		BountyEarned("bounty_earned"),
		BountyCancelled("bounty_cancelled"),
		PostDeleted("post_deleted"),
		PostUndeleted("post_undeleted"),
		AssociationBonus("association_bonus"),
		ArbitraryReputationChange("arbitrary_reputation_change"),
		VoteFraudReversal("vote_fraud_reversal"),
		PostMigrated("post_migrated"),
		UserDeleted("user_deleted"),
		Unknown("unknown");
		private String mFriendlyName;
		private ReputationHistoryType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum RevisionType{
		SingleUser("single_user"),
		VoteBased("vote_based"),
		Unknown("Unknown");
		private String mFriendlyName;
		private RevisionType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum FilterType{
		SingleUser("safe"),
		VoteBased("unsafe"),
		Invalid("invalid"),
		Unknown("Unknown");
		private String mFriendlyName;
		private FilterType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}

	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SortType{
		Activity("activity"),
		Creation("creation"),
		Votes("votes"),
		Rank("rank"), // For Badges
		Name("name"), // For Badges, Tags
		Type("type"), // For Badges
		Approval("approval"), // Post/Suggested Edits
		Rejection("rejection"), // Post/Suggested Edits
		Hot("hot"), // Questions
		Week("week"), // Questions
		Month("month"), // Questions
		Popular("popular"), // Tags
		Reputation("reputation"), // Users
		Modified("modified"), // Users
		Added("added"), // Users
		Relevance("relevance"), // Search
		Unknown("Unknown");
		private String mFriendlyName;
		private SortType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SortOrder{
		Desc("desc"),
		Asc("asc"),
		Unknown("unknown");
		private String mFriendlyName;
		private SortOrder(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum Period{
		AllTime("all_time"),
		Month("month"),
		Unknown("unknown");
		private String mFriendlyName;
		private Period(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
}
