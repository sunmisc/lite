.class final Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;
.super Ljava/lang/Object;
.source "MusicPlaylistContract.kt"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/vk/music/playlist/modern/MusicPlaylistContract4;-><init>(Lcom/vk/music/playlist/modern/MusicPlaylistContract3;Lcom/vk/dto/music/Playlist;Lcom/vk/music/common/MusicPlaybackLaunchContext;Lcom/vk/music/playlist/modern/MusicPlaylistContract;Lcom/vk/music/player/PlayerModel;Lcom/vk/music/playlist/ModernPlaylistModel;Lcom/vk/music/common/BoomModel;Lcom/vk/music/stats/MusicStatsTracker;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Consumer<",
        "Ljava/lang/Throwable;",
        ">;"
    }
.end annotation


# static fields
.field public static final a:Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;

    invoke-direct {v0}, Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;-><init>()V

    sput-object v0, Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;->INSTANCE:Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final a(Ljava/lang/Throwable;)V
    .locals 2

    const-string v0, "it"

    .line 1
    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->a(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    invoke-static {p1, v0}, Lcom/vk/music/logger/MusicLogger;->a(Ljava/lang/Throwable;[Ljava/lang/Object;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    .line 2
    invoke-static {p1, v0, v1, v0}, Lcom/vk/core/util/DebugUtils;->a(Ljava/lang/Throwable;Ljava/lang/String;ILjava/lang/Object;)V

    return-void
.end method

.method public bridge synthetic accept(Ljava/lang/Object;)V
    .locals 0

    .line 1
    check-cast p1, Ljava/lang/Throwable;

    invoke-virtual {p0, p1}, Lcom/vk/music/playlist/modern/MusicPlaylistContract$f;->a(Ljava/lang/Throwable;)V

    return-void
.end method