USE [master]
GO
/****** Object:  Database [MiniSocialNetwork]    Script Date: 9/30/2020 6:45:35 PM ******/
CREATE DATABASE [MiniSocialNetwork]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MiniSocialNetwork', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\MiniSocialNetwork.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'MiniSocialNetwork_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\MiniSocialNetwork_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [MiniSocialNetwork] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MiniSocialNetwork].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MiniSocialNetwork] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET ARITHABORT OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MiniSocialNetwork] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MiniSocialNetwork] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MiniSocialNetwork] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MiniSocialNetwork] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET RECOVERY FULL 
GO
ALTER DATABASE [MiniSocialNetwork] SET  MULTI_USER 
GO
ALTER DATABASE [MiniSocialNetwork] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MiniSocialNetwork] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MiniSocialNetwork] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MiniSocialNetwork] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MiniSocialNetwork] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'MiniSocialNetwork', N'ON'
GO
ALTER DATABASE [MiniSocialNetwork] SET QUERY_STORE = OFF
GO
USE [MiniSocialNetwork]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 9/30/2020 6:45:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[Email] [varchar](100) NOT NULL,
	[Name] [varchar](100) NULL,
	[Password] [varchar](100) NULL,
	[Status] [int] NULL,
	[Role] [varchar](100) NULL,
 CONSTRAINT [PK_tblAccount] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 9/30/2020 6:45:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Article](
	[ArticleID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](100) NULL,
	[Title] [varchar](100) NULL,
	[Description] [varchar](max) NULL,
	[Image] [varchar](100) NULL,
	[Date] [datetime] NULL,
	[StatusID] [int] NULL,
 CONSTRAINT [PK_Article] PRIMARY KEY CLUSTERED 
(
	[ArticleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 9/30/2020 6:45:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[ArticleID] [int] NULL,
	[Email] [varchar](100) NULL,
	[Content] [varchar](500) NULL,
	[Date] [datetime] NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Emotion]    Script Date: 9/30/2020 6:45:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Emotion](
	[EmotionID] [int] IDENTITY(1,1) NOT NULL,
	[ArticleID] [int] NULL,
	[Email] [varchar](100) NULL,
	[Emotion] [varchar](100) NULL,
 CONSTRAINT [PK_Emotion] PRIMARY KEY CLUSTERED 
(
	[EmotionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 9/30/2020 6:45:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[NotifyID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](100) NULL,
	[ArticleID] [int] NULL,
	[Date] [datetime] NULL,
	[Content] [varchar](100) NULL,
 CONSTRAINT [PK_Notify1] PRIMARY KEY CLUSTERED 
(
	[NotifyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 9/30/2020 6:45:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[StatusID] [int] NOT NULL,
	[Name] [nchar](10) NULL,
 CONSTRAINT [PK_tblStatú] PRIMARY KEY CLUSTERED 
(
	[StatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'astius@gmail.com', N'Astius', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 1, N'Member')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'hauhuong@gmail.com', N'Hau Huong', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 2, N'Admin')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'klark1480@gmail.com', N'Klark', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 2, N'Member')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'test1@gmail.com', N'Test1', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 1, N'Member')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'test2@gmail.com', N'Test1', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 1, N'Member')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'ucproqua789@yahoo.com', N'Astius', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 2, N'Member')
INSERT [dbo].[Account] ([Email], [Name], [Password], [Status], [Role]) VALUES (N'vinhkhang@gmail.com', N'Vinh Khang', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 1, N'Member')
SET IDENTITY_INSERT [dbo].[Article] ON 

INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (2, N'hauhuong@gmail.com', N'dong', N'gian doi', N'Article1.jpg', CAST(N'2020-09-27T20:30:13.613' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (5, N'hauhuong@gmail.com', N'Xuan', N'abccccc', N'Article2.jpg', CAST(N'2020-09-27T21:08:55.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (10, N'hauhuong@gmail.com', N'ha', N'la hom nay', NULL, CAST(N'2020-09-27T21:12:57.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (11, N'hauhuong@gmail.com', N'thu', N'la mua chung ta xa nhau', NULL, CAST(N'2020-09-27T23:36:08.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (12, N'hauhuong@gmail.com', N'hom nay toi buon', N'toi het buon roi            ', NULL, CAST(N'2020-09-27T23:37:54.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (13, N'hauhuong@gmail.com', N'MUA DONG', N'                        ', NULL, CAST(N'2020-09-29T22:31:53.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (25, N'hauhuong@gmail.com', N'', N'                        ', NULL, CAST(N'2020-09-29T23:37:32.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (26, N'vinhkhang@gmail.com', N'Khang', N'Khang test                        ', N'Article8.JPG', CAST(N'2020-09-30T00:56:59.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (27, N'vinhkhang@gmail.com', N'Kim Hang', N'Do Re Mi Fa Sol                        ', N'Article8.JPG', CAST(N'2020-09-30T11:24:57.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (28, N'vinhkhang@gmail.com', N'abcccc', N'abbbbbbb', NULL, CAST(N'2020-09-30T11:48:27.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (29, N'vinhkhang@gmail.com', N'aaaaaaa', N'peorittktkf', NULL, CAST(N'2020-09-30T11:48:34.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (30, N'vinhkhang@gmail.com', N'aasdklsdakl;fksla;fk;laskfl;sa', N'                        fdasfsadkl;fkdasl;ksla;flk', NULL, CAST(N'2020-09-30T11:48:40.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (31, N'vinhkhang@gmail.com', N'Niem vui', N'la ngay duoc gap moi nguoi', NULL, CAST(N'2020-09-30T18:02:52.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (32, N'vinhkhang@gmail.com', N'mai mai khong phai anh', N'thanh binh', NULL, CAST(N'2020-09-30T18:03:18.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (33, N'vinhkhang@gmail.com', N'DEADLINE', N'is coming                        ', NULL, CAST(N'2020-09-30T18:36:44.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (34, N'vinhkhang@gmail.com', N'DEADLINE', N'is coming soonn                        ', NULL, CAST(N'2020-09-30T18:36:52.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (35, N'vinhkhang@gmail.com', N'DEADLINE', N'is nearly                        ', NULL, CAST(N'2020-09-30T18:36:58.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (36, N'vinhkhang@gmail.com', N'DEADLINE', N'i''m scared                        ', NULL, CAST(N'2020-09-30T18:37:12.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (37, N'vinhkhang@gmail.com', N'DEADLINE', N'help me                        ', NULL, CAST(N'2020-09-30T18:37:17.000' AS DateTime), 2)
INSERT [dbo].[Article] ([ArticleID], [Email], [Title], [Description], [Image], [Date], [StatusID]) VALUES (38, N'vinhkhang@gmail.com', N'DEADLINE', N'gonna pass soon                        ', NULL, CAST(N'2020-09-30T18:37:26.000' AS DateTime), 2)
SET IDENTITY_INSERT [dbo].[Article] OFF
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([CommentID], [ArticleID], [Email], [Content], [Date]) VALUES (1018, 2, N'hauhuong@gmail.com', N'hello', CAST(N'2020-09-28T22:47:12.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [Email], [Content], [Date]) VALUES (1019, 2, N'vinhkhang@gmail.com', N'xin chao', CAST(N'2020-09-28T22:47:27.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [Email], [Content], [Date]) VALUES (1024, 5, N'vinhkhang@gmail.com', N'aaaa', CAST(N'2020-09-30T18:29:41.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [Email], [Content], [Date]) VALUES (1027, 26, N'vinhkhang@gmail.com', N'aaaaa', CAST(N'2020-09-30T18:44:01.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comment] OFF
SET IDENTITY_INSERT [dbo].[Emotion] ON 

INSERT [dbo].[Emotion] ([EmotionID], [ArticleID], [Email], [Emotion]) VALUES (4, 2, N'hauhuong@gmail.com', N'DISLIKE')
INSERT [dbo].[Emotion] ([EmotionID], [ArticleID], [Email], [Emotion]) VALUES (5, 2, N'vinhkhang@gmail.com', N'LIKE')
INSERT [dbo].[Emotion] ([EmotionID], [ArticleID], [Email], [Emotion]) VALUES (6, 5, N'vinhkhang@gmail.com', N'LIKE')
INSERT [dbo].[Emotion] ([EmotionID], [ArticleID], [Email], [Emotion]) VALUES (7, 26, N'vinhkhang@gmail.com', N'UNKNOWN')
SET IDENTITY_INSERT [dbo].[Emotion] OFF
SET IDENTITY_INSERT [dbo].[Notification] ON 

INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (34, N'hauhuong@gmail.com', 2, CAST(N'2020-09-28T21:37:00.447' AS DateTime), N'Vinh Khang like dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (35, N'hauhuong@gmail.com', 2, CAST(N'2020-09-28T21:37:11.587' AS DateTime), N'Vinh Khang dislike dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (36, N'hauhuong@gmail.com', 2, CAST(N'2020-09-28T22:47:27.000' AS DateTime), N'Vinh Khang commented dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (37, N'hauhuong@gmail.com', 2, CAST(N'2020-09-29T22:37:08.000' AS DateTime), N'Vinh Khang like dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (38, N'hauhuong@gmail.com', 2, CAST(N'2020-09-29T22:40:07.000' AS DateTime), N'Vinh Khang commented dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (39, N'hauhuong@gmail.com', 2, CAST(N'2020-09-29T22:40:14.000' AS DateTime), N'Vinh Khang commented dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (40, N'vinhkhang@gmail.com', 26, CAST(N'2020-09-30T00:59:07.453' AS DateTime), N'Admin deleted your article')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (41, N'vinhkhang@gmail.com', 27, CAST(N'2020-09-30T11:49:42.000' AS DateTime), N'Admin delelted your article')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (42, N'vinhkhang@gmail.com', 30, CAST(N'2020-09-30T11:49:49.000' AS DateTime), N'Admin delelted your article')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (43, N'vinhkhang@gmail.com', 28, CAST(N'2020-09-30T11:49:54.000' AS DateTime), N'Admin delelted your article')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (44, N'hauhuong@gmail.com', 2, CAST(N'2020-09-30T18:03:32.000' AS DateTime), N'Vinh Khang like dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (45, N'hauhuong@gmail.com', 2, CAST(N'2020-09-30T18:03:33.000' AS DateTime), N'Vinh Khang dislike dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (46, N'hauhuong@gmail.com', 2, CAST(N'2020-09-30T18:03:34.000' AS DateTime), N'Vinh Khang like dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (47, N'hauhuong@gmail.com', 2, CAST(N'2020-09-30T18:03:43.000' AS DateTime), N'Vinh Khang commented dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (48, N'hauhuong@gmail.com', 2, CAST(N'2020-09-30T18:07:18.000' AS DateTime), N'Vinh Khang commented dong')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (49, N'vinhkhang@gmail.com', 29, CAST(N'2020-09-30T18:09:43.000' AS DateTime), N'Admin delelted your article')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (50, N'hauhuong@gmail.com', 5, CAST(N'2020-09-30T18:29:41.000' AS DateTime), N'Vinh Khang commented Xuan')
INSERT [dbo].[Notification] ([NotifyID], [Email], [ArticleID], [Date], [Content]) VALUES (51, N'hauhuong@gmail.com', 5, CAST(N'2020-09-30T18:29:42.000' AS DateTime), N'Vinh Khang like Xuan')
SET IDENTITY_INSERT [dbo].[Notification] OFF
INSERT [dbo].[Status] ([StatusID], [Name]) VALUES (1, N'New       ')
INSERT [dbo].[Status] ([StatusID], [Name]) VALUES (2, N'Active    ')
INSERT [dbo].[Status] ([StatusID], [Name]) VALUES (3, N'Delete    ')
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblStatus] FOREIGN KEY([Status])
REFERENCES [dbo].[Status] ([StatusID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_tblAccount_tblStatus]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_Account] FOREIGN KEY([Email])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_Account]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Status_Article] FOREIGN KEY([StatusID])
REFERENCES [dbo].[Status] ([StatusID])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Status_Article]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Account] FOREIGN KEY([Email])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Account]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Article] FOREIGN KEY([ArticleID])
REFERENCES [dbo].[Article] ([ArticleID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Article]
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD  CONSTRAINT [FK_Emotion_Account] FOREIGN KEY([Email])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[Emotion] CHECK CONSTRAINT [FK_Emotion_Account]
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD  CONSTRAINT [FK_Emotion_Article] FOREIGN KEY([ArticleID])
REFERENCES [dbo].[Article] ([ArticleID])
GO
ALTER TABLE [dbo].[Emotion] CHECK CONSTRAINT [FK_Emotion_Article]
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD  CONSTRAINT [FK_Notify_Account] FOREIGN KEY([Email])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[Notification] CHECK CONSTRAINT [FK_Notify_Account]
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD  CONSTRAINT [FK_Notify_Article] FOREIGN KEY([ArticleID])
REFERENCES [dbo].[Article] ([ArticleID])
GO
ALTER TABLE [dbo].[Notification] CHECK CONSTRAINT [FK_Notify_Article]
GO
USE [master]
GO
ALTER DATABASE [MiniSocialNetwork] SET  READ_WRITE 
GO
